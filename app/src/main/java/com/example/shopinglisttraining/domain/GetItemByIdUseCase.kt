package com.example.shopinglisttraining.domain

import javax.inject.Inject

class GetItemByIdUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend fun getItemById(id: Int): ShopItem {
        return shopListRepository.getItemById(id)
    }
}