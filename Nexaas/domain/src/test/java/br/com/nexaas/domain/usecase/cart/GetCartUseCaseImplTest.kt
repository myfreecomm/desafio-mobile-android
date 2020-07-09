package br.com.nexaas.domain.usecase.cart

import br.com.nexaas.domain.di.DomainModule
import br.com.nexaas.domain.entity.CartModel
import br.com.nexaas.domain.repository.ICartRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItems
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetCartUseCaseImplTest : AutoCloseKoinTest() {

    private val getCartUseCase: IGetCartUseCase by inject()

    @Mock
    private lateinit var mockCartRepository: ICartRepository

    @Before
    fun setup() {
        startKoin {
            modules(
                module {
                    single<ICartRepository> {
                        mockCartRepository
                    }
                },
                DomainModule.module
            )
        }
    }

    @Test
    fun get_cart() = runBlocking {

        // Given
        val item = CartModel(
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
        val mockList = listOf(
            CartModel(
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

        // When
        `when`(mockCartRepository.getCart()).thenReturn(mockList)

        val list = getCartUseCase.execute()

        // Then
        assertEquals(1, list.size)
        assertThat(list, hasItems(item))
    }

}