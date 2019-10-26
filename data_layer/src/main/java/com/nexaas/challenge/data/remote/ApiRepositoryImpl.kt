package com.nexaas.challenge.data.remote

import com.nexaas.challenge.domain.model.ProductDomain
import com.nexaas.challenge.domain.repository.ApiRepository
import io.reactivex.Observable

internal class ApiRepositoryImpl(): ApiRepository {

    override fun getProductsList(): Observable<ArrayList<ProductDomain>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}