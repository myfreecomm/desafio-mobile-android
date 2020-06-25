package com.araujoraul.mvvmapp.extension

import android.widget.ImageView
import com.araujoraul.mvvmapp.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(imageUrl: String?){
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_account_circle)
        .into(this)
}