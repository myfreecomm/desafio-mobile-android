package com.nexaas.app.data.mappers

import com.nexaas.app.data.entity.CartItemPO
import com.nexaas.app.domain.entity.CartItem

class CartItemPOToVOMapper : BaseMapper<CartItemPO, CartItem>() {
    override fun transform(entity: CartItemPO): CartItem {
        return CartItem(
            name = entity.name,
            quantity = entity.quantity,
            stock = entity.stock,
            image_url = entity.image_url,
            price = entity.price,
            tax = entity.tax,
            shipping = entity.shipping,
            description = entity.description
        )
    }
}
