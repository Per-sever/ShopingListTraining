package com.example.shopinglisttraining.di

import android.app.Application
import com.example.shopinglisttraining.presentation.MainActivity
import com.example.shopinglisttraining.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance
            application: Application
        ): ApplicationComponent
    }
}