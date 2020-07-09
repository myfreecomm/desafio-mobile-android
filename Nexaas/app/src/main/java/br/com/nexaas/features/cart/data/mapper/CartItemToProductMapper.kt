package br.com.nexaas.features.cart.data.mapper

import br.com.nexaas.common.utils.Mapper
import br.com.nexaas.features.cart.data.entity.CartItemVO
import br.com.nexaas.features.product.data.ProductVO

class CartItemToProductMapper : Mapper<CartItemVO, ProductVO> {
    override fun transform(from: CartItemVO): ProductVO {
        return ProductVO(
            name = from.name,
            stock = from.stock,
            imageUrl = from.imageUrl,
            price = from.price,
            description = from.description
        )
    }

    override fun transform(from: List<CartItemVO>): List<ProductVO> {
        return from.map { transform(it) }
    }

}