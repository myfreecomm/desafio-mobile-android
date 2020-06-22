package com.araujoraul.mvvmapp.ui.cart

import com.araujoraul.mvvmapp.data.CartRepository
import com.araujoraul.mvvmapp.db.ItemDao
import com.araujoraul.mvvmapp.db.ItemEntity
import io.mockk.*
import junit.framework.Assert.*
import org.junit.Test


class CartTest {

    val repository = mockk<CartRepository>(relaxed = true)
    val itemDao = mockk<ItemDao>(relaxed = true)

    @Test
    fun `checking data`(){

        val listOfItemsEntity = repository.loadItems()

        coEvery { repository.loadItems() } returns listOfItemsEntity

      val result = repository.loadItems()

        assertNotNull(result)

    }

    @Test
    fun `check inserting database`(){

        val listResponse = listOf<ItemEntity>()

        val inserted = itemDao.insertIntoDatabase(listResponse)

        assertNotNull(inserted)

    }

    @Test
    fun `when loadItems is sucessfull called, liveData should be not null`(){
        val listItems = listOf<ItemEntity>()

        every { repository.loadItems() } returns listItems

    }

    @Test
    fun `getting data from database and check if is not null`(){

        val listItems = repository.loadItems()

        every { itemDao.getItemsFromDatabase() } returns listOf()

        itemDao.insertIntoDatabase(repository.loadItems())
        itemDao.getItemsFromDatabase()

        verify { itemDao.getItemsFromDatabase() }
        assertNull(itemDao.getItemsFromDatabase())

    }


}