package com.example.shopinglisttraining.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addItem(item: ShopItem)

    fun removeItem(item: ShopItem)

    fun getShopList(): LiveData<List<ShopItem>>

    fun editItem(item: ShopItem)

    fun getItemById(itemId: Int): ShopItem
}