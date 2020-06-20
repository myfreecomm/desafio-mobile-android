package com.nexaas.app

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.nexaas.app.data.CartItemDAO
import com.nexaas.app.data.CartRepositoryImpl
import com.nexaas.app.data.CartService
import com.nexaas.app.data.entity.CartItemDTO
import com.nexaas.app.data.entity.CartItemPO
import com.nexaas.app.data.mappers.CartItemDTOToPOMapper
import com.nexaas.app.data.mappers.CartItemPOToVOMapper
import com.nexaas.app.domain.entity.CartItem
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Before
import org.junit.Test

class CartRepositoryImplTest : BaseWebTest() {

    @MockK
    private lateinit var cartItemDAO: CartItemDAO

    @MockK
    private lateinit var cartItemDTOToPOMapper: CartItemDTOToPOMapper

    @MockK
    private lateinit var cartItemPOToVOMapper: CartItemPOToVOMapper


    private val cartRepositoryImpl by lazy {
        CartRepositoryImpl(
            cartItemDAO,
            retrofit.create(CartService::class.java),
            cartItemDTOToPOMapper,
            cartItemPOToVOMapper
        )
    }

    @Before
    fun onSetup() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        startMockWebServer()
    }

    @After
    fun tearDown() {
        closeMockWebServer()
    }

    @Test
    fun `GIVEN a valid response WHEN getting cart items THEN query retrofit client and return success`() {
        //Given
        mockServer.enqueue(
            MockResponse()
                .setBody(getJson(CART_ITEM_RESPONSE_JSON))
        )

        every {
            cartItemDTOToPOMapper.transform(any<CartItemDTO>())
        } returns MOCK_CART_ITEM_PO

        every {
            cartItemPOToVOMapper.transform(any<CartItemPO>())
        } returns MOCK_CART_ITEM

        // When
        val result = runBlocking {
            cartRepositoryImpl.getCart()
        }

        TestCase.assertTrue(result is SuspendableResult.Success<*, *>)
    }

    companion object {
        private const val CART_ITEM_RESPONSE_JSON = "CartItemResponse.json"

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

        private val MOCK_CART_ITEM = CartItem(
            "",
            1,
            1,
            "",
            1,
            1,
            1,
            ""
        )
    }
}