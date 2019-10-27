package com.nexaas.challenge.presentation.model

import com.nexaas.challenge.domain.model.ProductDomain

internal data class Product (val name: String?,
                             val quantity: Double?,
                             val stock: Double?,
                             val imageUrl: String?,
                             val price: Double?,
                             val tax: Double?,
                             val shipping: Double?,
                             val description: String?) {

    companion object {
        fun fromDomainObject(domainModel: ProductDomain): Product {
            return Product(
                domainModel.name,
                domainModel.quantity,
                domainModel.stock,
                domainModel.imageUrl,
                domainModel.price,
                domainModel.tax,
                domainModel.shipping,
                domainModel.description
            )
        }
    }

}