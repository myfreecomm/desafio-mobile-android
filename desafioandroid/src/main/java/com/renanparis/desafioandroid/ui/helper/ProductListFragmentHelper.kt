package com.renanparis.desafioandroid.ui.helper

import com.renanparis.desafioandroid.data.model.Product

class ProductListFragmentHelper {

    fun sumPrice(products: List<Product>): Int {
        var subtotal = 0
        for (product in products) {
            subtotal += product.price
        }
        return subtotal
    }

    fun sumShipping(products: List<Product>): Int {
        var shipping = 0
        for (product in products) {
            shipping += product.shipping
        }
        return shipping
    }

    fun sumTax(products: List<Product>): Int {
        var tax = 0
        for (product in products) {
            tax += product.shipping
        }
        return tax
    }

    fun getTotal(products: List<Product>): Int {
        var total = 0
        for (product in products) {
            total += product.shipping + product.price +product.tax
        }
        return total
    }
}