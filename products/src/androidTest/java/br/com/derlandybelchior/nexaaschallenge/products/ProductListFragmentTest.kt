package br.com.derlandybelchior.nexaaschallenge.products

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import br.com.derlandybelchior.nexaaschallenge.domain.product.FetchProduct
import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.mockito.Mockito.mock


@ExperimentalCoroutinesApi
@MediumTest
class ProductListFragmentTest {

    private var repository: ProductRepository

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

    private val localProductsList by lazy { listOf(product1, product2, product3)}

    init {
        repository = object : ProductRepository {
            override suspend fun fetchAll(forceUpdate: Boolean) = localProductsList
        }

        startKoin {
            loadKoinModules(
                module {
                    viewModel(override = true) { ProductViewModel(FetchProduct(repository)) }
                }
            )
        }
    }

    @Test fun testNavigationToProductDetail() = runBlockingTest {
        val navController = mock(NavController::class.java)
        //navController.setGraph(R.navigation.nav_graph)

        val productListFragmentScenario = launchFragmentInContainer<ProductListFragment>(Bundle(), R.style.AppTheme)
        productListFragmentScenario.onFragment {fragment ->
            Navigation.setViewNavController(fragment.view!!, navController)
        }

        onView(
            withId(R.id.list)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            hasDescendant(withText("Product 1")), click()
        ))

        verify(navController).navigate(
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                localProductsList.map { ProductPresentation(
                    name = it.name,
                    price = "${it.price}",
                    quantity = it.quantity,
                    shipping = "${it.shipping}",
                    stock = "In stock",
                    tax = "${it.tax}",
                    description = it.description,
                    imageUrl = it.image
                ) }[0]
            )
        )
    }
}