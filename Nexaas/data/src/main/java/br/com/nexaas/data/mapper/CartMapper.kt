package br.com.nexaas.data.mapper

import br.com.nexaas.data.source.remote.entity.response.CartItemResponse
import br.com.nexaas.domain.entity.CartModel
import br.com.nexaas.domain.mapper.base.DomainMapper

class CartMapper : DomainMapper<CartItemResponse, CartModel> {
    override fun toDomain(from: CartItemResponse): CartModel {
        return CartModel(
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

    override fun toDomain(from: List<CartItemResponse>): List<CartModel> {
        return from.map { toDomain(it) }
    }

}