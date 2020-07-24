package com.example.nexaas.presentation.pencils

import android.provider.Settings.Global.getString
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nexaas.R
import com.example.nexaas.data.ApiService
import com.example.nexaas.data.PencilServices
import com.example.nexaas.data.model.Pencil
import com.example.nexaas.data.response.PencilBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PencilsViewModel : ViewModel() {

    val pencilsLiveData: MutableLiveData<List<Pencil>> = MutableLiveData()
    val pencilsCartLiveData: MutableLiveData<Int> = MutableLiveData()
    val totalLiveData: MutableLiveData<Double> = MutableLiveData()
    val subTotalLiveData: MutableLiveData<Double> = MutableLiveData()
    val taxlLiveData: MutableLiveData<Double> = MutableLiveData()
    val shippinglLiveData: MutableLiveData<Double> = MutableLiveData()

    fun getPencils() {

        val pencils = ApiService.getRetrofitInstance("https://raw.githubusercontent.com").create(PencilServices::class.java).getPencils()

        pencils.enqueue(object : Callback<List<PencilBodyResponse>> {
            override fun onFailure(call: Call<List<PencilBodyResponse>>, t: Throwable) {






            }

            override fun onResponse(
                call: Call<List<PencilBodyResponse>>,
                response: Response<List<PencilBodyResponse>>) {

                if (response.isSuccessful) {
                    val pencils: MutableList<Pencil> = mutableListOf()
                    if (response.isSuccessful) {

                        val pencils: MutableList<Pencil> = mutableListOf()

                        response.body()?.let { pencilBodyResponse ->
                            var shippingTotal: Double = 0.0
                            var taxTotal: Double = 0.0
                            var priceTotal: Double = 0.0
                            var subTotal: Double = 0.0

                            for (results in pencilBodyResponse) {

                                taxTotal += results.tax
                                shippingTotal += results.shipping
                                priceTotal += results.price

                                pencils.add(Pencil(results.name, results.quantity,
                                            results.stock, results.imageUrl,
                                            results.price, results.tax,
                                            results.shipping, results.description))
                            }

                            taxlLiveData.value = taxTotal
                            shippinglLiveData.value = shippingTotal
                            totalLiveData.value = priceTotal + taxTotal + shippingTotal
                            subTotalLiveData.value = priceTotal

                            pencilsCartLiveData.value = pencils.size
                            pencilsLiveData.value = pencils
                        }
                    }

                }
            }
        })
    }
}