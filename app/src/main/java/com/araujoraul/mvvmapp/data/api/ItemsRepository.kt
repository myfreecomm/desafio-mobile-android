package com.araujoraul.mvvmapp.data.api

import com.araujoraul.mvvmapp.db.ItemDao
import java.util.concurrent.Executor
import javax.inject.Inject

class ItemsRepository @Inject constructor(
    private val webservice: ApiService,
    private val executor: Executor,
    private val itemDao: ItemDao
){



}