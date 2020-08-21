package com.example.challengeaccepted.platform.bindingadapter

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.example.challengeaccepted.platform.util.CustomMessage
import com.google.android.material.snackbar.Snackbar

@BindingAdapter("android:showMessage")
fun showMessage(viewGroup: ViewGroup, error: CustomMessage?) {
    if (error != null && error.message > 0) {
        Snackbar.make(viewGroup, viewGroup.context.getText(error.message), Snackbar.LENGTH_LONG).show()
    }
}