package com.nexaas.challenge.domain.interactor

import com.nexaas.challenge.domain.core.Interactor
import com.nexaas.challenge.domain.model.ProductDomain
import com.nexaas.challenge.domain.repository.ApiRepository
import io.reactivex.Observable
import io.reactivex.Single

class GetProductsList(private val apiRepository: ApiRepository): Interactor<Observable<List<ProductDomain>>> {

    companion object {
        const val TAG = "GET_PRODUCTS_LIST"
    }

    override fun execute(): Observable<List<ProductDomain>> {
        return apiRepository.getProductsList()
    }

}