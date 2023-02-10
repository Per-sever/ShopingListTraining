package com.example.shopinglisttraining.di

import androidx.lifecycle.ViewModel
import com.example.shopinglisttraining.presentation.MainViewModel
import com.example.shopinglisttraining.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    @Binds
    fun bindShopItemViewModel(impl: ShopItemViewModel): ViewModel
}