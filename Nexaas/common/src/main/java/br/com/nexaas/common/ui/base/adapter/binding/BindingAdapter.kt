package br.com.nexaas.common.ui.base.adapter.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.nexaas.common.utils.MoneyFormat
import coil.api.load

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String?) {
    if (url.isNullOrEmpty()) return
    load(url)
}

@BindingAdapter("android:text")
fun TextView.money(value: Long) {
    text = MoneyFormat().formatted(value)
}

@BindingAdapter("isGone")
fun View.bindIsGone(isGone: Boolean) {
    visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}