package br.com.derlandybelchior.nexaaschallenge.data

import br.com.derlandybelchior.nexaaschallenge.domain.error.ProductsNotFound
import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductDataSource
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultProductRepositoryTest {

    private val product1 = Product(
        name = "Product 1",
        description = "Product 1 description",
        image = "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true",
        price = 36.00,
        quantity = 10,
        shipping = 2.00,
        stock = 15,
        tax = 3.24
    )

    private val product2 = Product(
        name = "Product 2",
        description = "Product 2 description",
        image = "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true",
        price = 36.00,
        quantity = 1,
        shipping = 2.00,
        stock = 15,
        tax = 3.24
    )

    private val product3 = Product(
        name = "Product 3",
        description = "Product 3 description",
        image = "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true",
        price = 36.00,
        quantity = 1,
        shipping = 2.00,
        stock = 15,
        tax = 3.24
    )

    private val localProductsList by lazy { listOf(product1, product2)}
    private val remoteProductsList by lazy { listOf(product1, product2, product3)}

    private lateinit var remoteDataSource: ProductDataSource
    private lateinit var localDataSource: ProductDataSource
    private lateinit var productRepository: ProductRepository

    @Before fun setup () {
        remoteDataSource = FakeDataSource(remoteProductsList.toMutableList())
        localDataSource = FakeDataSource(localProductsList.toMutableList())
        productRepository = DefaultProductRepository(remoteDataSource, localDataSource)
    }

    @Test fun `fetchAll should request all products from remote data source when forceupdate is true`() = runBlockingTest {
        val products = productRepository.fetchAll(forceUpdate = true)

        assertThat(products).isEqualTo(remoteProductsList)
    }

    @Test fun `fetchAll should request all products from local data source when forceupdate is false`() = runBlockingTest {
        val products = productRepository.fetchAll(forceUpdate = false)

        assertThat(products).isEqualTo(localProductsList)
    }

    @Test fun `fetchAll should throw exception when retrieve a empty list`() = runBlockingTest {

        val result = kotlin.runCatching {
            remoteDataSource = FakeDataSource(mutableListOf())
            localDataSource = FakeDataSource(mutableListOf())
            productRepository = DefaultProductRepository(remoteDataSource, localDataSource)
            productRepository.fetchAll(forceUpdate = false)

            fail("Should throw an Excepton") as Throwable
        }
        assertThat(result.exceptionOrNull()).isEqualTo(ProductsNotFound)
    }

    @Test fun `save should save a list of products retrieved from server`() = runBlockingTest {

        remoteDataSource = mock()
        localDataSource = mock()
        productRepository = DefaultProductRepository(remoteDataSource, localDataSource)
        whenever(remoteDataSource.fetchProducts()).thenReturn(remoteProductsList)
        productRepository.fetchAll(forceUpdate = true)

        verify(localDataSource, times(1)).save(remoteProductsList)
    }
}