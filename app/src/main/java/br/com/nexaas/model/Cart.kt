package br.com.nexaas.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

open class Cart : Serializable {

    @SerializedName("name")
    var name: String? = null
        internal set

    @SerializedName("quantity")
    var quantity: Int = 0
        internal set

    @SerializedName("stock")
    var stock: Int = 0
        internal set

    @SerializedName("image_url")
    lateinit var image_url: String
        internal set

    @SerializedName("price")
    var price: Int = 0
        internal set

    @SerializedName("tax")
    var tax: Int = 0
        internal set

    @SerializedName("shipping")
    var shipping: Int = 0
        internal set

    @SerializedName("description")
    lateinit var description: String
        internal set

}
