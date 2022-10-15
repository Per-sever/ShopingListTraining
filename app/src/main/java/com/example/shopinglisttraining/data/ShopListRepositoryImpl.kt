package com.example.shopinglisttraining.data

import com.example.shopinglisttraining.domain.ShopItem
import com.example.shopinglisttraining.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("Name $i", i, true)
            addItem(item)
        }
    }

    override fun addItem(item: ShopItem) {
        if (item.id == ShopItem.UNDEFINED_ID) {
            item.id = autoIncrementId++
        }
        shopList.add(item)
    }

    override fun removeItem(item: ShopItem) {
        shopList.remove(item)
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }

    override fun editItem(item: ShopItem) {
        val oldElement = getItemById(item.id)
        shopList.remove(oldElement)
        addItem(item)
    }

    override fun getItemById(itemId: Int): ShopItem {
        return shopList.find { it.id == itemId } ?: throw RuntimeException(
            "Element with id " +
                    "$itemId no found"
        )
    }
}