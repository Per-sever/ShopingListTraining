package com.example.shopinglisttraining.domain

interface ShopListRepository {
    fun addItem(item: ShopItem)

    fun removeItem(item: ShopItem)

    fun getShopList(): List<ShopItem>

    fun editItem(item: ShopItem)

    fun getItemById(itemId: Int): ShopItem
}