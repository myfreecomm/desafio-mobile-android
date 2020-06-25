package com.gui.antonio.testenexaas.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gui.antonio.testenexaas.database.ProductDao
import com.gui.antonio.testenexaas.database.ProductEntity
import com.gui.antonio.testenexaas.network.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductModel constructor(val productDao: ProductDao) {

    val error = MutableLiveData<String>()
    val dbDontHaveItems = MutableLiveData<Boolean>()

    fun products(): LiveData<List<ProductEntity>> {

        val data = MutableLiveData<List<ProductEntity>>()

            RetrofitClient.retrofit.products().enqueue(object : Callback<List<ProductEntity>> {
                override fun onFailure(call: Call<List<ProductEntity>>, t: Throwable) { error.value = t.message }
                override fun onResponse(call: Call<List<ProductEntity>>, response: Response<List<ProductEntity>>) {

                    if(response.isSuccessful){
                        CoroutineScope(Dispatchers.IO).launch {
                            productDao.deleteAll()
                            response.body()?.let { it -> productDao.insertAll(it) }
                            data.postValue(productDao.getAll())
                        }
                    }else{
                        error.value = response.message()
                    }
                }
            })

        return data
    }

    fun productsFromDB(): LiveData<List<ProductEntity>> {
        val data = MutableLiveData<List<ProductEntity>>()
        CoroutineScope(Dispatchers.IO).launch{
            val items = productDao.getAll()
            if(items.isEmpty()) dbDontHaveItems.postValue(true)
            data.postValue(items)
        }
        return data
    }

}