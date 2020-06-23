package com.nexaas.app.features

import com.nexaas.app.domain.entity.CartItem

interface CartUiEvents {
    fun clickItem(cartItem: CartItem)
}
