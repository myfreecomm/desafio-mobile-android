package com.example.cartapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.Observer
import com.example.cartapp.di.Modules
import com.example.cartapp.model.cartrepository.ItemModel
import com.example.cartapp.model.cartrepository.dao.CartDao
import com.example.cartapp.viewmodel.CartDetailsViewModel
import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.inject
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CartDetailsViewModelTest : KoinTest {


    private val viewModel: CartDetailsViewModel by inject()

    private val cartDao: CartDao by inject()

    private val observer: Observer<ItemModel> = mock()

    private val item: ItemModel = mock()

    @Before
    fun before() {
        val mockApplication = module(override = true) {

            single { mock(Application::class.java) }
            single { mock(Context::class.java) }
            single { mock(androidContext()::class.java) }
        }

        startKoin {
            mockApplication
            modules(Modules.all)
        }
        viewModel.itemCartLiveData.observeForever(observer)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `fetchItemModelFromDatabase_ShouldReturnItem`() = runBlocking {
        val expectedUser = ItemModel(1,any(),any(),any(),any(),any(),any(),any(),any())

        `when`(expectedUser.id?.let { cartDao.getItem(it) }).thenReturn(expectedUser)


        expectedUser.id?.let {
            viewModel.fetch(it)
        }


        val captor = ArgumentCaptor.forClass(ItemModel::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(expectedUser, value)
        }
    }
}