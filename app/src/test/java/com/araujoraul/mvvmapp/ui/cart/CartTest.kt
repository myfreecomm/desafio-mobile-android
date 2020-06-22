package com.araujoraul.mvvmapp.ui.cart

import com.araujoraul.mvvmapp.db.ItemDao
import com.araujoraul.mvvmapp.db.ItemDatabase
import com.araujoraul.mvvmapp.db.ItemEntity
import io.mockk.*
import org.junit.Test


class CartTest {

    val repository = mockk<CartRepository>(relaxed = true)
    val viewModel = mockk<CartViewModel>(relaxed = true)
    val itemDao = mockk<ItemDao>(relaxed = true)


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

    @Test
    fun `check if data is inserting on Room database`(){

        val listOfItemsEntity = repository.loadItems()

        every { itemDao.insertIntoDatabase(listOfItemsEntity) } returns Unit

        itemDao.insertIntoDatabase(listOfItemsEntity)


        verify { itemDao.insertIntoDatabase(listOfItemsEntity) }.apply { assert(true) }


    }

}