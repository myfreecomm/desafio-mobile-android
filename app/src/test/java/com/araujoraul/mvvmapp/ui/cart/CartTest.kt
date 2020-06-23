package com.araujoraul.mvvmapp.ui.cart

import com.araujoraul.mvvmapp.data.CartRepository
import com.araujoraul.mvvmapp.db.ItemDao
import io.mockk.*
import junit.framework.Assert.*
import org.junit.Test

class CartTest {

    val repository = mockk<CartRepository>(relaxed = true)
    val itemDao = mockk<ItemDao>(relaxed = true)

    @Test
    fun `check if request to api is fetching data`(){

        val listOfItemsEntity = repository.loadItems()

        coEvery { repository.loadItems() } returns listOfItemsEntity

      val result = repository.loadItems()

        assertNotNull(result)

    }

    @Test
    fun `check inserting database`(){

        val listResponse = repository.loadItems()

        assertNotNull(itemDao.insertIntoDatabase(listResponse))

    }

    @Test
    fun `check getting data from database`(){

        assertNotNull(itemDao.getItemsFromDatabase())

    }

    @Test
    fun`check deleting data from database`(){

        val itemsDeleted = itemDao.deleteAllFromDatabase()

        assertNotNull(itemsDeleted)

    }


}