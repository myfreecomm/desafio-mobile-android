package com.nexaas.challenge.presentation.model

import com.nexaas.challenge.domain.model.ProductDomain

internal data class Product (val name: String?,
                             val quantity: Number?,
                             val stock: Number?,
                             val imageUrl: String?,
                             val price: Number?,
                             val tax: Number?,
                             val shipping: Number?,
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