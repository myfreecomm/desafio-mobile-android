package com.araujoraul.mvvmapp.ui.cart

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.araujoraul.mvvmapp.data.api.ApiService
import com.araujoraul.mvvmapp.db.ItemDatabase
import com.araujoraul.mvvmapp.db.ItemEntity
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemRepository(var application: Application) {

    val showProgress = MutableLiveData<Boolean>()
    val itemList = MutableLiveData<List<ItemEntity>>()
    val cartSize = MutableLiveData<Int>()
    val noInternet = MutableLiveData<Boolean>()

    fun loadItems(){

        showProgress.value = true

        val call = ApiService.createInstance()
            .getItemsResults()

            call.enqueue(object : Callback<List<ItemEntity>>{

                override fun onFailure(call: Call<List<ItemEntity>>, t: Throwable) {
                    Toast.makeText(application, "Error while getting data. \n" +
                            "Maybe you don't have an active internet connection?", Toast.LENGTH_LONG).show()
                    showProgress.value = false
                    noInternet.value = true
                }

                override fun onResponse(call: Call<List<ItemEntity>>, response: Response<List<ItemEntity>>) {
                    Log.d("ItemRepository", "RESPONSE${Gson().toJson(response.body())}")
                    showProgress.value = false
                    insert(response.body()!!)
                    itemList.value = response.body()


                    val size: Int = response.body()!!.size

                    cartSize.value = size

                }

            })
    }

fun insert(list: List<ItemEntity>) = CoroutineScope(Dispatchers.Main).launch {
    ItemDatabase.getDatabase(application).getItemDao().insertIntoDatabase(list)
}


}