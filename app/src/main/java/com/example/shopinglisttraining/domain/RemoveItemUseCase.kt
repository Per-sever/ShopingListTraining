package com.example.shopinglisttraining.domain

import javax.inject.Inject

class RemoveItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {
    suspend fun removeItem(itemId: ShopItem) {
        shopListRepository.removeItem(itemId)
    }
}