package br.com.cart.model

import java.io.Serializable

class Product(val name: String, val quantity: Int, val stock: Int, val image_url: String, val price: Double, val tax: Double, val shipping: Int, val description: String ): Serializable {

}