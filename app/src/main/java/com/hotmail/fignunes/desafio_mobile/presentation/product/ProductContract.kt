package com.hotmail.fignunes.desafio_mobile.presentation.product

import com.hotmail.fignunes.desafio_mobile.model.Product

interface ProductContract {
    fun initializeAdapter(products: List<Product>)
    fun message(error: Int)
    fun initializeSwipe()
    fun swipeOff()
}