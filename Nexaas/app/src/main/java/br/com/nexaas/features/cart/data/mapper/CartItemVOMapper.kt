package br.com.nexaas.features.cart.data.mapper

import br.com.nexaas.common.utils.Mapper
import br.com.nexaas.domain.entity.CartModel
import br.com.nexaas.features.cart.data.entity.CartItemVO

class CartItemVOMapper : Mapper<CartModel, CartItemVO> {
    override fun transform(from: CartModel): CartItemVO {
        return CartItemVO(
            quantity = from.quantity,
            shipping = from.shipping,
            imageUrl = from.imageUrl,
            price = from.price,
            name = from.name,
            description = from.description,
            tax = from.tax,
            stock = from.stock
        )
    }

    override fun transform(from: List<CartModel>): List<CartItemVO> {
        return from.map { transform(it) }
    }

}