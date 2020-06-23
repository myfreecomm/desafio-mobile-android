package com.nexaas.app.mappers

import com.nexaas.app.data.entity.CartItemDTO
import com.nexaas.app.data.entity.CartItemPO
import com.nexaas.app.data.mappers.CartItemDTOToPOMapper
import junit.framework.TestCase
import org.junit.Test

class CartItemDTOToPOMapperTest {

    private val cartItemDTOToPOMapper by lazy { CartItemDTOToPOMapper() }

    private val FAKE_CART_ITEM_DTO = CartItemDTO(
        "",
        1,
        1,
        "",
        1,
        1,
        1,
        ""
    )

    private val MOCK_CART_ITEM_PO = CartItemPO(
        "",
        1,
        1,
        "",
        1,
        1,
        1,
        ""
    )

    @Test
    fun `GIVEN cart items data transfer object WHEN transforming it get proper cart items persistent object`() {
        //Given
        //When
        val cartItemPO = cartItemDTOToPOMapper.transform(FAKE_CART_ITEM_DTO)
        //Then
        TestCase.assertEquals(MOCK_CART_ITEM_PO, cartItemPO)
    }
}