package com.example.shopinglisttraining.domain

class EditItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun editItem(item: ShopItem) {
        shopListRepository.editItem(item)
    }
}