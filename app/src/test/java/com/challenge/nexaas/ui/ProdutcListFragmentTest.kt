package com.challenge.nexaas.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.challenge.nexaas.MainCoroutineRule
import com.challenge.nexaas.data.Product
import com.challenge.nexaas.data.ProductRepository
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProdutcListFragmentTest {

    private lateinit var mViewModel: ProdutcListViewModel

    @Mock
    lateinit var repository: ProductRepository

    @get:Rule
    val coroutinesTextRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var isLoading: LiveData<Boolean>
    private lateinit var onError: LiveData<String>

    @Before
    fun before() {
        repository = Mockito.mock(ProductRepository::class.java)
    }

    @Test
    fun `test get products`() = runBlockingTest {
        //given
        coroutinesTextRule.pauseDispatcher()

        val type = object : TypeToken<List<Product>>() {}.type
        val response = Gson().fromJson(productsStringList, type) as List<Product>
        Mockito.`when`(repository.getProducts()).thenReturn(response)
        //when

        mViewModel = ProdutcListViewModel(repository)
        isLoading = mViewModel.isLoading
        onError = mViewModel.onError

        //then
        assertThat(mViewModel.isLoading.value).isTrue()
        coroutinesTextRule.resumeDispatcher()

        assertThat(mViewModel.products.value).isEqualTo(response)
        assertThat(mViewModel.isLoading.value).isFalse()
        return@runBlockingTest
    }


    private val productsStringList = "[ { \"name\": \"Pencil\", \"quantity\": 1, \"stock\": 5, \"image_url\": \"https://github.com/charleston10/test-android-nexaas/blob/master/assets/pencil.png?raw=true\", \"price\": 150, \"tax\": 162, \"shipping\": 50, \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odioc.\" }, { \"name\": \"Rubberbands\", \"quantity\": 1, \"stock\": 8, \"image_url\": \"https://github.com/charleston10/test-android-nexaas/blob/master/assets/rubberbands.png?raw=true\", \"price\": 450, \"tax\": 81, \"shipping\": 0, \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odio. Nulla ut tincidunt erat, a mollis tortor. Phasellus vel ligula leo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nam id semper quam, id efficitur mi. Etiam volutpat eleifend commodo. Duis sed consectetur diam. Morbi mattis justo eget vehicula placerat. Sed commodo, neque a accumsan vehicula, magna libero lacinia dolor, a consectetur turpis odio nec risus. Nullam id dui lacus.\" }, { \"name\": \"Rulers\", \"quantity\": 1, \"stock\": 1, \"image_url\": \"https://github.com/charleston10/test-android-nexaas/blob/master/assets/rulers.png?raw=true\", \"price\": 800, \"tax\": 0, \"shipping\": 100, \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odioc.\" }, { \"name\": \"Clock\", \"quantity\": 1, \"stock\": 10, \"image_url\": \"https://github.com/charleston10/test-android-nexaas/blob/master/assets/clock.png?raw=true\", \"price\": 2200, \"tax\": 81, \"shipping\": 50, \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odio. Nulla ut tincidunt erat, a mollis tortor. Phasellus vel ligula leo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nam id semper quam, id efficitur mi. Etiam volutpat eleifend commodo. Duis sed consectetur diam. Morbi mattis justo eget vehicula placerat. Sed commodo, neque a accumsan vehicula, magna libero lacinia dolor, a consectetur turpis odio nec risus. Nullam id dui lacus.\" } ]"
}