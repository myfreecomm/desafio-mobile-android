package com.nexaas.app.data.mappers

import com.nexaas.app.data.entity.CartItemDTO
import com.nexaas.app.data.entity.CartItemPO

class CartItemDTOToPOMapper : BaseMapper<CartItemDTO, CartItemPO>() {
    override fun transform(entity: CartItemDTO): CartItemPO {
        return CartItemPO(
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
