package com.araujoraul.mvvmapp.data.api

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.araujoraul.mvvmapp.db.ItemDatabase
import com.araujoraul.mvvmapp.db.ItemEntity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemRepository(var application: Application) {


val itemList = MutableLiveData<List<ItemEntity>>()

    fun loadItems(){

        val call = ApiService.createInstance().getItemsResults()

            call.enqueue(object : Callback<List<ItemEntity>>{

                override fun onFailure(call: Call<List<ItemEntity>>, t: Throwable) {
                    Toast.makeText(application, "Error while getting data", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<ItemEntity>>, response: Response<List<ItemEntity>>) {
                    Log.d("ItemRepository", "RESPONSE${Gson().toJson(response.body())}")
                    insert(response.body()!!)
                    itemList.value = response.body()
                }

            })
    }

fun insert(list: List<ItemEntity>) = CoroutineScope(Dispatchers.Main).launch {
    ItemDatabase.getDatabase(application).getItemDao().insertIntoDatabase(list)
}


}