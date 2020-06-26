package br.com.derlandybelchior.nexaaschallenge.domain.product

import br.com.derlandybelchior.nexaaschallenge.domain.error.ProductsNotFound
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class FetchProductTest {

    private lateinit var fetchProduct: FetchProduct
    private val repository: ProductRepository = mock()
    private val remoteProducts by lazy {
        listOf(
            Product(
                name = "Product 1",
                description = "Product 1 description",
                image = "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true",
                price = 36.00,
                quantity = 10,
                shipping = 2.00,
                stock = 15,
                tax = 3.24
            )
        )
    }

    private val localProducts by lazy {
        listOf(
            Product(
                name = "Product 1",
                description = "Product 1 description",
                image = "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true",
                price = 36.00,
                quantity = 10,
                shipping = 2.00,
                stock = 15,
                tax = 3.24
            ),
            Product(
                name = "Product 2",
                description = "Product 1 description",
                image = "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true",
                price = 36.00,
                quantity = 1,
                shipping = 2.00,
                stock = 15,
                tax = 3.24
            )
        )
    }

    @Before fun setup() {

        fetchProduct = FetchProduct(repository)
    }

    @Test fun `fetchAll request all products from repository`() = runBlockingTest {
        whenever(repository.fetchAll(forceUpdate = true)).thenReturn(remoteProducts)

        val result = fetchProduct.fetchAll(forceUpdate = true)

        assertThat(result).isEqualTo(remoteProducts)
    }

    @Test fun `fetchAll request all products from repository should throw ProductsNotFound when retrieve an empty list`() = runBlockingTest {
        val result = runCatching {
            whenever(repository.fetchAll(forceUpdate = true)).thenReturn(emptyList())

            val result = fetchProduct.fetchAll(forceUpdate = true)

            fail("Should throw an ProductsNotFound") as Throwable
        }

        assertThat(result.exceptionOrNull()).isEqualTo(ProductsNotFound)

    }
}