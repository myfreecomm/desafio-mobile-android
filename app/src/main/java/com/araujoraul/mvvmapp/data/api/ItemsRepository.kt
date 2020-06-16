package com.araujoraul.mvvmapp.data.api

import androidx.lifecycle.LiveData
import com.araujoraul.mvvmapp.db.ItemDao
import com.araujoraul.mvvmapp.db.ItemEntity
import java.util.concurrent.Executor
import javax.inject.Inject

class ItemsRepository @Inject constructor(
    private val webservice: ApiService,
    private val executor: Executor,
    private val itemDao: ItemDao
){

    fun getItem(itemId: Int): LiveData<ItemEntity>{
        refreshItem(itemId)
        return itemDao.getItems(itemId)
    }

    private fun refreshItem(itemId: Int){



    }


}