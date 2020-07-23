package com.example.nexaaschallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nexaaschallenge.repo.ItemCart
import com.example.nexaaschallenge.repo.Repo
import com.example.nexaaschallenge.repo.retrofit.Resource
import com.example.nexaaschallenge.repo.room.Dao
import com.example.nexaaschallenge.viewModel.CartViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class WeatherViewModelTest {
    private lateinit var viewModel: CartViewModel
    private lateinit var repo: Repo
    private lateinit var dao: Dao
    private lateinit var cartObserver: Observer<Resource<List<ItemCart>>>
    private val mockItem = ItemCart(1,"test", "test", "test",1.0f, 1, 1.0f, 1, 1.0f )
    private val successResource = Resource.success(arrayListOf(mockItem))

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UIThread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        repo = mock()
        dao = mock()
        cartObserver = mock()
        runBlocking {
            whenever(repo.getCart()).thenReturn(successResource)
        }
        viewModel = CartViewModel(repo, dao)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `when getCart is called with internet connection, then observer is updated with success`() = runBlocking {
        viewModel.networkAvailable.value = true
        viewModel.cartResource.observeForever(cartObserver)
        delay(10)
        verify(repo).getCart()
        verify(cartObserver, timeout(50)).onChanged(Resource.loading(null))
        verify(cartObserver, timeout(50)).onChanged(successResource)
    }
}