package com.example.shopinglisttraining.domain

class GetItemByIdUseCase(private val shopListRepository: ShopListRepository) {
    fun getItemById(id: Int): ShopItem {
        return shopListRepository.getItemById(id)
    }
}