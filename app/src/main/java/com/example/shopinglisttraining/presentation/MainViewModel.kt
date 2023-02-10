package com.example.shopinglisttraining.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopinglisttraining.domain.EditItemUseCase
import com.example.shopinglisttraining.domain.GetShopListUseCase
import com.example.shopinglisttraining.domain.RemoveItemUseCase
import com.example.shopinglisttraining.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val removeItemUseCase: RemoveItemUseCase,
    private val editItemUseCase: EditItemUseCase
) : ViewModel() {

//    private val repository = ShopListRepositoryImpl(application)

//    private val getShopListUseCase = GetShopListUseCase(repository)
//    private val removeItemUseCase = RemoveItemUseCase(repository)
//    private val editItemUseCase = EditItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun removeShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            removeItemUseCase.removeItem(shopItem)
        }
    }


    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editItemUseCase.editItem(newItem)
        }

    }

}