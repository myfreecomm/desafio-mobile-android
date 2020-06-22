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
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartRepository(var application: Application) {

    private val itemDatabase by lazy { ItemDatabase.getDatabase(application).getItemDao() }

    val liveData = MutableLiveData<List<ItemEntity>>()

    val showProgress = MutableLiveData<Boolean>()
    val cartSize = MutableLiveData<Int>()
    val noInternet = MutableLiveData<Boolean>()
    val subTotal = MutableLiveData<Int>()
    val shipping = MutableLiveData<Int>()
    val tax = MutableLiveData<Int>()


    fun loadItems(): List<ItemEntity> {




            showProgress.postValue(true)

            val call = ApiService.createInstance().getItemsResults()
            call.enqueue(object : Callback<List<ItemEntity>>{

                override fun onResponse(call: Call<List<ItemEntity>>, response: Response<List<ItemEntity>>) {

                    if (response.isSuccessful){
                        Log.d("ItemRepository", "RESPONSE${Gson().toJson(response.body())}")
                        showProgress.postValue(false)
                        noInternet.postValue(false )

                        val itemList = response.body()!!
                        liveData.postValue(itemList)
                        insert(itemList)

                        val size: Int = itemList.size
                        var maxShipping = -1
                        var maxTax = -1

                        cartSize.postValue(size)

                        for (i in itemList.indices){
                            val tempShipping = itemList[i]
                            val tempTax = itemList[i]

                            if (tempShipping.shipping!! > maxShipping) maxShipping = tempShipping.shipping
                            if (tempTax.tax!! > maxTax) maxTax = tempTax.tax
                        }
                        Log.d("RESPONSE", "MAX SHIPPING: $maxShipping")
                        Log.d("RESPONSE", "MAX TAX: $maxTax")
                        shipping.value = maxShipping
                        tax.postValue(maxTax)

                        val pricesTotal = itemList.sumBy { it.price!! }
                        Log.d("RESPONSE", "TOTAL: $pricesTotal")
                        subTotal.postValue(pricesTotal)



                        // handle the case when the API request gets a error response.
                    } else {
                        Toast.makeText(application, "Internal server error, try later again...", Toast.LENGTH_LONG).show()
                    }

                }

                // handle the case when the API request gets a exception response.
                override fun onFailure(call: Call<List<ItemEntity>>, t: Throwable) {
                    showProgress.postValue(false)
                    noInternet.postValue(true)
                    loadItems()
                }

            })


return listOf()

    }

    fun insert(list: List<ItemEntity>) = CoroutineScope(Dispatchers.Main).launch {
        itemDatabase.insertIntoDatabase(list)
    }

    fun getData() = CoroutineScope(Dispatchers.Main).launch {
        itemDatabase.getItemsFromDatabase()
    }


}