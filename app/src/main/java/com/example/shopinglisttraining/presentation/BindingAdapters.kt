package com.example.shopinglisttraining.presentation

import androidx.databinding.BindingAdapter
import com.example.shopinglisttraining.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("checkCorrectCount")
fun bindCheckCorrectCount(textInputLayout: TextInputLayout, isCorrect: Boolean) {
    val message = if (isCorrect) {
        textInputLayout.context.getString(R.string.error_msg_input)
    } else {
        null
    }
    textInputLayout.error = message
}
