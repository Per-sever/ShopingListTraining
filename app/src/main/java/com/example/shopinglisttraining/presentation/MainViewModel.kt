package com.example.shopinglisttraining.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.shopinglisttraining.data.ShopListRepositoryImpl
import com.example.shopinglisttraining.domain.EditItemUseCase
import com.example.shopinglisttraining.domain.GetShopListUseCase
import com.example.shopinglisttraining.domain.RemoveItemUseCase
import com.example.shopinglisttraining.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeItemUseCase = RemoveItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    private val scope = CoroutineScope(Dispatchers.IO)

    fun removeShopItem(shopItem: ShopItem) {
        scope.launch {
            removeItemUseCase.removeItem(shopItem)
        }
    }


    fun changeEnableState(shopItem: ShopItem) {
        scope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editItemUseCase.editItem(newItem)
        }

    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}