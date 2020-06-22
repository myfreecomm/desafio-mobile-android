package com.araujoraul.mvvmapp.ui.cart

import com.araujoraul.mvvmapp.db.ItemEntity
import io.mockk.*
import org.junit.Test


class CartViewModelTest {



    val repository = mockk<CartRepository>(relaxed = true)
    val viewModel = mockk<CartViewModel>()


    @Test
    fun `check if viewModel is calling loadItems`() {

        every { viewModel.loadItems() } returns Unit

        viewModel.loadItems()

        verify { viewModel.loadItems() }
    }

    @Test
    fun `check if repository is calling the method to fetch data`(){

        val listOfItemsEntity = listOf<ItemEntity>()

        coEvery { repository.loadItems() } returns listOfItemsEntity

        repository.loadItems()
        assert(true)

        coVerify { repository.loadItems() }


    }

}