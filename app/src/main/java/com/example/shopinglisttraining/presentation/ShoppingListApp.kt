package com.example.shopinglisttraining.presentation

import android.app.Application
import com.example.shopinglisttraining.di.DaggerApplicationComponent

class ShoppingListApp : Application() {
     val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}