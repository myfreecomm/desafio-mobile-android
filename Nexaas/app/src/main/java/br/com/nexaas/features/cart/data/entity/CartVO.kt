package br.com.nexaas.features.cart.data.entity

import android.os.Parcelable
import androidx.annotation.Keep
import br.com.nexaas.common.utils.sumByLong
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class CartVO(
    val items :List<CartItemVO>
) : Parcelable {
    fun itemsCount() = items.count()

    fun total() = subtotal() + shipping() + tax()

    fun subtotal() = items.sumByLong { it.price * it.quantity }

    fun shipping() = items.sumByLong { it.shipping }

    fun tax() = items.sumByLong { it.tax }
}