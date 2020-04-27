package com.hotmail.fignunes.desafio_mobile.presentation.movie.actions

import com.hotmail.fignunes.desafio_mobile.model.Product
import com.hotmail.fignunes.desafio_mobile.repository.remote.product.responses.ProductResponses

class ResponsesToProducts {

    fun execute(responses: List<ProductResponses>): List<Product> {
        return responses.map {
            Product(
                it.name,
                it.quantity,
                it.stock,
                it.image_url,
                it.price,
                it.tax,
                it.shipping,
                it.description
            )
        }
    }
}