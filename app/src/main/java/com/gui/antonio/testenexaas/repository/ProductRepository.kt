package com.gui.antonio.testenexaas.repository

import androidx.lifecycle.LiveData
import com.gui.antonio.testenexaas.database.ProductEntity
import com.gui.antonio.testenexaas.model.ProductModel
import javax.inject.Inject

class ProductRepository constructor(val productModel: ProductModel) {

    fun products(): LiveData<List<ProductEntity>> = productModel.products()
    fun error() = productModel.error
    fun dbDontHaveItems() = productModel.dbDontHaveItems
    fun productsFromDB(): LiveData<List<ProductEntity>> = productModel.productsFromDB()

}