package com.nexaas.challenge.domain.interactor

import com.nexaas.challenge.domain.core.Interactor
import com.nexaas.challenge.domain.model.ProductDomain
import com.nexaas.challenge.domain.repository.ApiRepository
import io.reactivex.Single

class GetProductsList(private val apiRepository: ApiRepository): Interactor<Single<List<ProductDomain>>> {

    companion object {
        const val TAG = "GET_PRODUCTS_LIST"
    }

    override fun execute(): Single<List<ProductDomain>> {
        return apiRepository.getProductsList()
    }

}