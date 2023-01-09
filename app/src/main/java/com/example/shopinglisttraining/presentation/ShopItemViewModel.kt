package com.example.shopinglisttraining.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopinglisttraining.data.ShopListRepositoryImpl
import com.example.shopinglisttraining.domain.AddItemUseCase
import com.example.shopinglisttraining.domain.EditItemUseCase
import com.example.shopinglisttraining.domain.GetItemByIdUseCase
import com.example.shopinglisttraining.domain.ShopItem

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val addItemUseCase = AddItemUseCase(repository)
    private val editItemShopUseCase = EditItemUseCase(repository)
    private val getItemByIdUseCase = GetItemByIdUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItemById = MutableLiveData<ShopItem>()
    val shopItemById: LiveData<ShopItem>
        get() = _shopItemById

    private val _accessToClose = MutableLiveData<Unit>()
    val accessToClose: LiveData<Unit>
        get() = _accessToClose

    fun addItemUseCase(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addItemUseCase.addItem(shopItem)
            finishWork()
        }
    }

    fun editItemShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _shopItemById.value?.let {
                val item = it.copy(name = name, count = count)
                editItemShopUseCase.editItem(item)
                finishWork()
            }
        }
    }

    fun getItemByIdUseCase(shopItemId: Int) {
        val item = getItemByIdUseCase.getItemById(shopItemId)
        _shopItemById.value = item
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _accessToClose.value = Unit
    }
}