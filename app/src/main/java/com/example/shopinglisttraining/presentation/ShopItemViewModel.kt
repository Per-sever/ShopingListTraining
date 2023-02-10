package com.example.shopinglisttraining.presentation

import androidx.lifecycle.*
import com.example.shopinglisttraining.domain.AddItemUseCase
import com.example.shopinglisttraining.domain.EditItemUseCase
import com.example.shopinglisttraining.domain.GetItemByIdUseCase
import com.example.shopinglisttraining.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopItemViewModel @Inject constructor(
    private val addItemUseCase: AddItemUseCase,
    private val editItemUseCase: EditItemUseCase,
    private val getItemByIdUseCase: GetItemByIdUseCase
) : ViewModel() {

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
            viewModelScope.launch {
                val shopItem = ShopItem(name, count, true)
                addItemUseCase.addItem(shopItem)
                finishWork()
            }

        }
    }

    fun editItemShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _shopItemById.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = name, count = count)
                    editItemUseCase.editItem(item)
                    finishWork()
                }
            }
        }
    }

    fun getItemByIdUseCase(shopItemId: Int) {
        viewModelScope.launch {
            val item = getItemByIdUseCase.getItemById(shopItemId)
            _shopItemById.value = item
        }
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