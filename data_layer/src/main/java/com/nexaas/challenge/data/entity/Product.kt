package com.nexaas.challenge.data.entity

import com.google.gson.annotations.SerializedName
import com.nexaas.challenge.data.core.Entity
import com.nexaas.challenge.domain.model.ProductDomain

internal data class Product (@SerializedName("name") val name: String?,
                             @SerializedName("quantity") val quantity: Double?,
                             @SerializedName("stock") val stock: Double?,
                             @SerializedName("image_url") val imageUrl: String?,
                             @SerializedName("price") val price: Double?,
                             @SerializedName("tax") val tax: Double?,
                             @SerializedName("shipping") val shipping: Double?,
                             @SerializedName("description") val description: String?): Entity<ProductDomain>() {

    override fun asDomainObject(): ProductDomain {
        return ProductDomain(
                this.name,
                this.quantity,
                this.stock,
                this.imageUrl,
                this.price,
                this.tax,
                this.shipping,
                this.description
            )
    }

}