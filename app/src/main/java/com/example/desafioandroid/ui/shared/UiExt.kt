package com.example.desafioandroid.ui.shared

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import java.text.DecimalFormat

fun ImageView.loadNetworkImage(url : String){
    Glide.with(this).load(url).into(this)
}

fun Double.toCurrency() : String{
    val formatter = DecimalFormat("$#,##")
    return formatter.format(this)
}

fun <T> MutableLiveData<T>.toLiveData() : LiveData<T> = this