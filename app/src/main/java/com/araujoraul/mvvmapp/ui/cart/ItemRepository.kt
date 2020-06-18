package com.araujoraul.mvvmapp.ui.cart

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.araujoraul.mvvmapp.data.api.ApiService
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

    val showProgress = MutableLiveData<Boolean>()
    val itemList = MutableLiveData<List<ItemEntity>>()
    val cartSize = MutableLiveData<Int>()
    val noInternet = MutableLiveData<Boolean>()
    val subTotal = MutableLiveData<Int>()
    val shipping = MutableLiveData<Int>()
    val tax = MutableLiveData<Int>()


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

                    val listResponse = response.body()
                    val size: Int = response.body()!!.size
                    var maxShipping = -1
                    var maxTax = -1

                    showProgress.value = false
                    insert(response.body()!!)
                    itemList.value = response.body()
                    cartSize.value = size

                    for (i in listResponse!!.indices){
                        val tempShipping = listResponse[i]
                        val tempTax = listResponse[i]


                        if (tempShipping.shipping!! > maxShipping) maxShipping = tempShipping.shipping
                        if (tempTax.tax!! > maxTax) maxTax = tempTax.tax
                    }
                    Log.d("RESPONSE", "MAX SHIPPING: $maxShipping")
                    Log.d("RESPONSE", "MAX TAX: $maxTax")
                    shipping.value = maxShipping
                    tax.value = maxTax


                    val pricesTotal = listResponse.sumBy { it.price!! }
                    Log.d("RESPONSE", "TOTAL: $pricesTotal")
                    subTotal.value = pricesTotal

                }

            })
    }

fun insert(list: List<ItemEntity>) = CoroutineScope(Dispatchers.Main).launch {
    ItemDatabase.getDatabase(application).getItemDao().insertIntoDatabase(list)
}


}