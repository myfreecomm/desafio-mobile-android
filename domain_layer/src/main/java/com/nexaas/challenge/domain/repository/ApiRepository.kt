package com.nexaas.challenge.domain.repository

import com.nexaas.challenge.domain.model.ProductDomain
import io.reactivex.Observable

interface ApiRepository: Repository {
    fun getProductsList(): Observable<List<ProductDomain>>
}