package com.example.shopinglisttraining.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopinglisttraining.domain.ShopItem
import com.example.shopinglisttraining.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

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
        updateList()
    }

    override fun removeItem(item: ShopItem) {
        shopList.remove(item)
        updateList()
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
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

    private fun updateList() {
        shopListLiveData.value = shopList.toList()
    }
}