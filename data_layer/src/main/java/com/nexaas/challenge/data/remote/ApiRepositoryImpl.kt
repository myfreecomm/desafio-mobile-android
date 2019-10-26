package com.nexaas.challenge.data.remote

import android.util.Log
import com.nexaas.challenge.data.remote.service.ApiServicesFactory
import com.nexaas.challenge.domain.interactor.GetProductsList
import com.nexaas.challenge.domain.model.ProductDomain
import com.nexaas.challenge.domain.repository.ApiRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal class ApiRepositoryImpl (private val apiServicesFactory: ApiServicesFactory): ApiRepository {

    override fun onRequestSuccess(tag: String, obj: String) {
        Log.d(tag, "onRequestSuccess: $obj")
    }

    override fun onRequestError(tag: String, e: Throwable) {
        Log.e(tag, "onRequestError: ${e.localizedMessage}")
        e.printStackTrace()
    }

    override fun getProductsList(): Single<List<ProductDomain>> {
        return apiServicesFactory
            .getApiServices()
            .getProductsList()
            .doOnSuccess { onRequestSuccess(GetProductsList.TAG, it.toString()) }
            .doOnError { onRequestError(GetProductsList.TAG, it) }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { list ->
                // Convert DataEntity to DomainModel
                list.asSequence()
                    .map { entity -> entity.asDomainObject() }
                    .toList()
                    .let { modifiedList ->
                        // TODO: Add cache logic
                        Single.just(modifiedList)
                    }
            }
    }

}