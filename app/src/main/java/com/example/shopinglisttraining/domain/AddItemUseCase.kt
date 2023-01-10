package com.example.shopinglisttraining.domain

class AddItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun addItem(item: ShopItem) {
        shopListRepository.addItem(item)
    }
}