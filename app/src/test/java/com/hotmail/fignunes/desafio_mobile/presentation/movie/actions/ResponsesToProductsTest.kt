package com.hotmail.fignunes.desafio_mobile.presentation.movie.actions

import com.hotmail.fignunes.desafio_mobile.model.Product
import com.hotmail.fignunes.desafio_mobile.repository.remote.product.responses.ProductResponses
import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class ResponsesToProductsTest {

    @Test
    fun `should return product`() {

        val response1 = ProductResponses("Ruler", 110,340, "https://rulers", BigDecimal(5.0),1, 4, "Description rulers")
        val response2 = ProductResponses("Pencil", 10,34, "https://pencil", BigDecimal(15.0),2, 3, "Description pencil")
        val responses = listOf(response1, response2)

        val product1 = Product("Ruler", 110,340, "https://rulers", BigDecimal(5.0),1, 4, "Description rulers")
        val product2 = Product("Pencil", 10,34, "https://pencil", BigDecimal(15.0),2, 3, "Description pencil")
        val products = listOf(product1, product2)

        Assert.assertEquals(products, ResponsesToProducts().execute(responses))
    }

}