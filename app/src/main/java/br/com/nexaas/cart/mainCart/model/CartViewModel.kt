package br.com.nexaas.cart.mainCart.model

import br.com.nexaas.cart.model.CartItem
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CartViewModel : Serializable {
    @SerializedName("cartItems")
    lateinit var cartItems: MutableList<CartItem>
    @SerializedName("countItems")
    var countItems: Int = 0
    @SerializedName("total")
    var total: String? = null
    @SerializedName("subTotal")
    var subTotal: String? = null
    @SerializedName("shipping")
    var shipping: String? = null
    @SerializedName("tax")
    var tax: String? = null

    //Cart Item
    @SerializedName("image")
    var image: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("price")
    var price: String? = null
    @SerializedName("stock")
    var stock: String? = null
    @SerializedName("description")
    var description: String? = null
}