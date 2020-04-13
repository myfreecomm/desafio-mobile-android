package com.example.testnexaas.modules.product.networking

import android.annotation.SuppressLint
import com.example.testnexaas.core.network.BaseNetwork
import com.example.testnexaas.modules.product.model.Product
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
                    onSuccess(it)
                }
            }, { error ->
                onError(error)
            })
    }
}