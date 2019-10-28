package com.nexaas.challenge.presentation.model

import android.os.Parcelable
import com.nexaas.challenge.domain.model.ProductDomain
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class Product (val name: String,
                             val quantity: Int,
                             val stock: Int,
                             val imageUrl: String,
                             val price: Double,
                             val tax: Double,
                             val shipping: Double,
                             val description: String): Parcelable {

    companion object {
        const val BUNDLE_KEY = "Product"

        fun fromDomainObject(domainModel: ProductDomain): Product {
            return Product(
                domainModel.name!!,
                domainModel.quantity!!,
                domainModel.stock!!,
                domainModel.imageUrl!!,
                domainModel.price!!,
                domainModel.tax!!,
                domainModel.shipping!!,
                domainModel.description!!
            )
        }
    }

}