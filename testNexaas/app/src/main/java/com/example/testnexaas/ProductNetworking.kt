package com.example.testnexaas

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object ProductNetworking : BaseNetwork() {

    private val API by lazy { getRetrofitBuilder().build().create(ProductApi::class.java) }

    @SuppressLint("CheckResult")
    fun getProductsFromApi(
        onSuccess: (productsResponse: List<Product>) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        API.getProductsFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ products ->
                products?.let {
                    onSuccess(products)
                }
            }, { error ->
                onError(error)
            })
    }
}