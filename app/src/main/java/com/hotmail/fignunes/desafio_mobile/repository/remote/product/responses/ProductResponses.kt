package com.hotmail.fignunes.desafio_mobile.repository.remote.product.responses

import java.math.BigDecimal

data class ProductResponses (
    var name: String,
    var quantity: Int,
    var stock: Int,
    var image_url: String,
    var price: BigDecimal,
    var tax: Int,
    var shipping: Int,
    var description: String
)