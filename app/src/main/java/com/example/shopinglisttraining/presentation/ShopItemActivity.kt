package com.example.shopinglisttraining.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.shopinglisttraining.R

class ShopItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        val idExtra = intent.getIntExtra(EXTRA_ID_ITEM, -1)
        Log.d("ShopItemActivity", mode.toString())
        Log.d("ShopItemActivityID", "$idExtra")
    }


    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_ID_ITEM = "id_item"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_ID_ITEM, shopItemId)
            return intent
        }
    }
}