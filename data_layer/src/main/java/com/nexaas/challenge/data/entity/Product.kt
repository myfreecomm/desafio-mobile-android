package com.nexaas.challenge.data.entity

import com.google.gson.annotations.SerializedName
import com.nexaas.challenge.data.core.Entity
import com.nexaas.challenge.domain.model.ProductDomain

internal data class Product (@SerializedName("name") val name: String?,
                    @SerializedName("quantity") val quantity: Number?,
                    @SerializedName("stock") val stock: Number?,
                    @SerializedName("image_url") val imageUrl: String?,
                    @SerializedName("price") val price: Number?,
                    @SerializedName("tax") val tax: Number?,
                    @SerializedName("shipping") val shipping: Number?,
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