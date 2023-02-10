package com.example.shopinglisttraining.di

import android.app.Application
import com.example.shopinglisttraining.data.AppDatabase
import com.example.shopinglisttraining.data.ShopListDao
import com.example.shopinglisttraining.data.ShopListRepositoryImpl
import com.example.shopinglisttraining.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {
    @ApplicationScope
    @Binds
    abstract fun bindShopListRepositoryImpl(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideShopListDao(application: Application): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}