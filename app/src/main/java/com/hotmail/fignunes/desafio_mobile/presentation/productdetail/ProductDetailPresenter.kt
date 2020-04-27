package com.hotmail.fignunes.desafio_mobile.presentation.productdetail

import com.hotmail.fignunes.desafio_mobile.R
import com.hotmail.fignunes.desafio_mobile.common.BasePresenter
import com.hotmail.fignunes.desafio_mobile.common.toEuaStringAmount
import com.hotmail.fignunes.desafio_mobile.model.Product
import com.hotmail.fignunes.desafio_mobile.presentation.common.StringHelper

class ProductDetailPresenter(
    private val contract: ProductDetailContract,
    private val stringHelper: StringHelper
) : BasePresenter() {

    var name: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    var price: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    var stock: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    var description: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    fun onCreate(product: Product) {
        name = product.name
        price = product.price.toEuaStringAmount()
        description = product.description

        if(product.stock == 1) {
            stock = stringHelper.getString(R.string.only_one_in_stock)
        } else {
            stock = stringHelper.getString(R.string.in_stock)
        }
        contract.loadImage(product.image_url)
    }

    fun removeFromCart() {
        contract.message(R.string.remove_from_cart)
    }
}

