package com.nexaas.app.data

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.nexaas.app.data.mappers.CartItemDTOToPOMapper
import com.nexaas.app.data.mappers.CartItemPOToVOMapper
import com.nexaas.app.domain.entity.CartItem
import com.nexaas.app.domain.repository.CartRepository

class CartRepositoryImpl(
    private var cartItemDAO: CartItemDAO,
    private var cartService: CartService,
    private var cartItemDTOToPOMapper: CartItemDTOToPOMapper,
    private var cartItemPOToVOMapper: CartItemPOToVOMapper

) : CartRepository {

    override suspend fun getCart(): SuspendableResult<List<CartItem>, Exception> {
        val cartItems = cartService.getItems().map(cartItemDTOToPOMapper::transform)
        cartItems.forEach(cartItemDAO::insert)
        return SuspendableResult.of {
            cartItemDAO.getCartItems().map(cartItemPOToVOMapper::transform)
        }
    }
}