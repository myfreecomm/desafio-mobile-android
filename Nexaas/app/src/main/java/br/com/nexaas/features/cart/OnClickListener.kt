package br.com.nexaas.features.cart

import br.com.nexaas.features.cart.data.entity.CartItemVO

interface OnClickListener {
    fun onClickProduct(itemVO: CartItemVO)
}