package com.nexaas.desafio.model.repository

import com.nexaas.desafio.model.Product
import com.nexaas.desafio.model.db.ProductLocalRepo
import com.nexaas.desafio.model.retrofit.service.ProductService
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class ProductRepository(private val productService: ProductService, private val localDB: ProductLocalRepo) {

    private fun getListProductAPI(): Observable<List<Product>> {
        return productService.getListProduct()
            .toObservable()
            .doOnNext {
                saveInDB(it)
            }
            .onErrorResumeNext {
                    throwable: Throwable ->
                getListProductDB()
            }
    }

    private fun getListProductDB(): Observable<List<Product>> {
        return localDB.getAllProducts().filter { it.isNotEmpty() }
            .toObservable()
    }

     fun getAllProducts(): Maybe<List<Product>>? {
        return Observable.concatArray(
            getListProductDB(),
            getListProductAPI()
        ).firstElement()
    }

    private fun saveInDB(products: List<Product>) {
        Observable.fromCallable { localDB.addProducts(products) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

    }
}



