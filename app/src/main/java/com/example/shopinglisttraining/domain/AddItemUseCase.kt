package com.example.shopinglisttraining.domain

import javax.inject.Inject

class AddItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend fun addItem(item: ShopItem) {
        shopListRepository.addItem(item)
    }
}