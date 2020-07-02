package com.example.desafioandroid.domain.repository

import com.example.desafioandroid.data.local.LocalDataSource
import com.example.desafioandroid.data.local.cart.entity.ProductEntity
import com.example.desafioandroid.data.remote.RemoteDataSource
import com.example.desafioandroid.domain.model.Product
import com.example.desafioandroid.domain.model.fromRemote
import com.example.desafioandroid.domain.model.toDomain
import com.example.desafioandroid.domain.shared.Either
import com.example.desafioandroid.domain.shared.ErrorEvent
import com.example.desafioandroid.domain.shared.SuccesEvent

class CartRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getCartProducts(): Either<Throwable, List<Product>> {
        lateinit var result: Either<Throwable, List<Product>>

        result = try {
            SuccesEvent(remoteDataSource.getCartProducts().map { it.fromRemote() })
        } catch (t: Throwable) {
            ErrorEvent(t)
        }
        return result

    }

    fun saveProductList(productList: List<ProductEntity>): Either<Throwable, Unit> {
        lateinit var result: Either<Throwable, Unit>

        result = try {
            SuccesEvent(localDataSource.insertProductList(productList))
        } catch (t: Throwable) {
            ErrorEvent(t)
        }

        return result
    }

    fun getLocalProductList(): Either<Throwable, List<Product>> {
        lateinit var result: Either<Throwable, List<Product>>

        result = try {
            val data = localDataSource.getCartProductList().map {
                it.toDomain()
            }
            SuccesEvent(data)
        } catch (t: Throwable) {
            ErrorEvent(t)
        }

        return result
    }

}