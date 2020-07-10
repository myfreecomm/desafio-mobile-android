package br.com.nexaas.data.mapper

import br.com.nexaas.data.source.remote.entity.response.CartItemResponse
import br.com.nexaas.domain.entity.CartModel
import br.com.nexaas.domain.mapper.base.DomainMapper
import br.com.nexaas.data.mapper.base.EntityMapper
import br.com.nexaas.data.source.local.entity.CartEntity

class CartMapper : DomainMapper<CartItemResponse, CartModel>, EntityMapper<CartModel, CartEntity> {
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

    override fun toEntity(from: CartModel): CartEntity {
        return CartEntity(
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

    override fun toEntity(from: List<CartModel>): List<CartEntity> {
        return from.map { toEntity(it) }
    }

    override fun reverseEntity(from: CartEntity): CartModel {
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

    override fun reverseEntity(from: List<CartEntity>): List<CartModel> {
        return from.map { reverseEntity(it) }
    }


}