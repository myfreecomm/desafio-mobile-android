package com.example.testnexaas.modules.product.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.testnexaas.modules.product.database.ProductDatabase
import com.example.testnexaas.modules.product.database.ProductsDao
import com.example.testnexaas.modules.product.model.Product
import com.example.testnexaas.modules.product.networking.ProductNetworking
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class ProductRepository(
    private val db: ProductsDao
) {

    fun getProducts(
        onSuccess: (products: List<Product>) -> Unit,
        onError: (error: String) -> Unit,
        showOfflineProducts: (products: List<Product>) -> Unit
    ) {

        ProductNetworking.getProductsFromApi(
            onSuccess = { productsResponse ->
                updateProductsToDb(productsResponse)
                getProductsFromDb(onSuccess, onError)
            },
            onError = { error ->

                onError(error.message.toString())

                getProductsFromDb(onSuccess = {
                    showOfflineProducts(it)
                }, onError = {
                    Log.e("GET-PRODUCTS-DB-ERROR", it)
                })
            }
        )
    }

    @SuppressLint("CheckResult")
    private fun getProductsFromDb(
        onSuccess: (products: List<Product>) -> Unit,
        onError: (error: String) -> Unit
    ) {
        db.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ productsDb ->
                onSuccess(productsDb)
            }, {
                onError(it.message.toString())
            })
    }

    private fun updateProductsToDb(products: List<Product>) {

        Completable.fromAction {
            db.deleteAndInsert(products)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    Log.d("SAVE-PRODUCTS", "VDC")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}