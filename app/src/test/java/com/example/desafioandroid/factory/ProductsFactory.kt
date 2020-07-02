package com.example.desafioandroid.factory

import com.example.desafioandroid.ui.cart.model.ProductBinding
import java.util.*
import kotlin.random.Random

object ProductsFactory {
    fun makeProduct() : ProductBinding {
        return ProductBinding(
            name = UUID.randomUUID().toString(),
            image_url = UUID.randomUUID().toString(),
            description = "text",
            price = Random.nextDouble(),
            quantity = Random.nextInt(),
            shipping = Random.nextDouble(),
            stock = Random.nextInt(),
            tax = Random.nextDouble()

        )
    }
}