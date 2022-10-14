package com.example.shopinglisttraining.domain

class AddItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addItem(item: ShopItem) {
        shopListRepository.addItem(item)
    }
}