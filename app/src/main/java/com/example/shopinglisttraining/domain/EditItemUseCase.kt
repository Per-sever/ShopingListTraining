package com.example.shopinglisttraining.domain

import javax.inject.Inject

class EditItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {
    suspend fun editItem(item: ShopItem) {
        shopListRepository.editItem(item)
    }
}