package com.example.shopinglisttraining.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    suspend fun addItem(item: ShopItem)

    suspend fun removeItem(item: ShopItem)

    fun getShopList(): LiveData<List<ShopItem>>

    suspend fun editItem(item: ShopItem)

    suspend fun getItemById(itemId: Int): ShopItem
}