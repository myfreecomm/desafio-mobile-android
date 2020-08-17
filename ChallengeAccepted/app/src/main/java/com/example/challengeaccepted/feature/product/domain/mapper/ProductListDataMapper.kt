package com.example.challengeaccepted.feature.product.domain.mapper

import android.os.Parcelable
import com.example.challengeaccepted.feature.product.data.model.ProductData
import com.example.challengeaccepted.platform.extensions.toMonetaryString
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProductListDataMapper(
    val quantityStr: String,
    val subtotalStr: String,
    val taxStr: String,
    val shippingStr: String,
    val totalStr: String,
    val products: List<ProductDataMapper?>
) : Parcelable {

    companion object {
        fun map(data: List<ProductData?>?): ProductListDataMapper? {
            if(data == null)
                return null
            val qt = data.sumBy { it?.price?.toInt() ?: 0 }
            val tax = data.sumBy { it?.tax?.toInt() ?: 0 }
            val shipping = data.sumBy { it?.shipping?.toInt() ?: 0 }

            return ProductListDataMapper(
                quantityStr = "${data.size} ${if(data.size == 1) "item" else "itens"} no seu carrinho",
                subtotalStr = qt.toMonetaryString(),
                taxStr = tax.toMonetaryString(),
                shippingStr = shipping.toMonetaryString(),
                totalStr = (qt + tax + shipping).toMonetaryString(),
                products = data.map { ProductDataMapper.map(it) }
            )
        }
    }
}
