package br.com.nexaas.data.mapper

import br.com.nexaas.data.source.remote.entity.response.CartItemResponse
import br.com.nexaas.domain.entity.CartModel
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class CartMapperTest {

    private val cartMapper = CartMapper()

    @Test
    fun test_toDomain() {
        // Given
        val responseList = listOf(
            CartItemResponse(
                name = "Pencil",
                quantity = 1,
                stock = 5,
                imageUrl = "https://github.com/charleston10/test-android-nexaas/blob/master/assets/pencil.png?raw=true",
                price = 150,
                tax = 162,
                shipping = 50,
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, " +
                        "gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, " +
                        "ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odioc."

            )
        )

        val domainList: List<CartModel> = cartMapper.toDomain(responseList)

        // Then
        assertThat(
            domainList, contains(
                allOf(
                    hasProperty("name", `is`("Pencil")),
                    hasProperty("quantity", `is`(1)),
                    hasProperty("stock", `is`(5)),
                    hasProperty(
                        "imageUrl",
                        `is`("https://github.com/charleston10/test-android-nexaas/blob/master/assets/pencil.png?raw=true")
                    ),
                    hasProperty("price", `is`(150L)),
                    hasProperty("tax", `is`(162L)),
                    hasProperty("shipping", `is`(50L))
                )
            )
        )

    }
}