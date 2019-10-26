package com.valderas.leonardo.nexaasdesafio.main.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.valderas.leonardo.nexaasdesafio.R

object ImageLoad {
    @BindingAdapter("imageUrl", "imageResource")
    @JvmStatic
    fun loadImage(view: ImageView, image: String, placeHolder:Int) {
        Picasso.get()
                .load(if (image.isEmpty()) "null" else image)
                .error(R.drawable.ic_error_avatar)
                .placeholder(placeHolder)
                .into(view)


    }
}