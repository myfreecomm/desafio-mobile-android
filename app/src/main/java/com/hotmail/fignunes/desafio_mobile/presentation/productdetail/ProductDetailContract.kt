package com.hotmail.fignunes.desafio_mobile.presentation.productdetail

interface ProductDetailContract {
    fun message(code: Int)
    fun loadImage(url: String)
}