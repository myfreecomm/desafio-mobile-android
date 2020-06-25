package com.araujoraul.mvvmapp.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.araujoraul.mvvmapp.data.CartRepository
import com.araujoraul.mvvmapp.db.ItemDao
import com.araujoraul.mvvmapp.db.ItemEntity
import io.mockk.*
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class CartTest {

    val repository = mockk<CartRepository>(relaxed = true)
    val itemDao = mockk<ItemDao>(relaxed = true)

    @Test
    fun `check if api request is fetching data`(){

        val listOfItemsEntity = repository.loadItems()

        coEvery { repository.loadItems() } returns listOfItemsEntity

      val result = repository.loadItems()

        assertNotNull(result)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `check inserting database`(){


     runBlockingTest {

         val listResponse = repository.loadItems()

         val result = itemDao.insertIntoDatabase(listResponse)
         val testingOtherResult = listOf("Test 1", "Test 2")

         assertNotNull(result)
         assertNotSame(result, testingOtherResult)

     }

    }

    @Test
    fun `check getting data from database`(){

        val dataFromDatabase = itemDao.getItemsFromDatabase()

        every { itemDao.getItemsFromDatabase() } returns dataFromDatabase

        assertNotNull(itemDao.getItemsFromDatabase())

    }

}