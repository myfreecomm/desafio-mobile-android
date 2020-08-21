package com.example.challengeaccepted.feature.product.domain.mapper

import android.os.Parcelable
import com.example.challengeaccepted.feature.product.data.model.ProductData
import com.example.challengeaccepted.platform.base.BaseMapper
import com.example.challengeaccepted.platform.extensions.toMonetaryString
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProductDataMapper(
    val name: String = "",
    val description: String = "",
    val stock: Int = -1,
    val imageUrl: String = "",
    val price: Long = -1L
) : BaseMapper<ProductData, ProductDataMapper>(), Parcelable {

    @IgnoredOnParcel val priceStr = price.toMonetaryString()
    @IgnoredOnParcel val stockStr = when {
        stock < 1 -> "no stock"
        stock == 1 -> "only 1 left"
        else -> "in stock"
    }

    override fun map(data: ProductData?): ProductDataMapper {
        if (data == null)
            return ProductDataMapper()

        return ProductDataMapper(
            name = data.name,
            description = data.description,
            stock = data.stock,
            imageUrl = data.imageUrl,
            price = data.price
        )
    }
}


