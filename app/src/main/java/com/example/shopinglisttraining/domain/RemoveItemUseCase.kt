package com.example.shopinglisttraining.domain

class RemoveItemUseCase(private val shopListRepository: ShopListRepository) {
    fun removeItem(itemId: ShopItem) {
        shopListRepository.removeItem(itemId)
    }
}