package com.example.shopinglisttraining.presentation

import androidx.lifecycle.ViewModel
import com.example.shopinglisttraining.data.ShopListRepositoryImpl
import com.example.shopinglisttraining.domain.EditItemUseCase
import com.example.shopinglisttraining.domain.GetShopListUseCase
import com.example.shopinglisttraining.domain.RemoveItemUseCase
import com.example.shopinglisttraining.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeItemUseCase = RemoveItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem) {
        removeItemUseCase.removeItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editItemUseCase.editItem(newItem)
    }

}