package com.appdesafio.cart.services
import android.annotation.SuppressLint
import com.appdesafio.cart.model.Produto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Network : ApiConfig() {
    private val apiConfig by lazy {
        getRetrofitBuilder()
            .build()
            .create(ApiData::class.java)
    }
    @SuppressLint("CheckResult")
    fun getProdutosService(
        onSuccess: (productsResponse: List<Produto>) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
    apiConfig.getDataProdutos()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ data ->
            data?.let {
                onSuccess(it)
            }
        }, { erro ->
            onError(erro)
        })
    }
}