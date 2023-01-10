package com.example.shopinglisttraining.domain

class RemoveItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun removeItem(itemId: ShopItem) {
        shopListRepository.removeItem(itemId)
    }
}