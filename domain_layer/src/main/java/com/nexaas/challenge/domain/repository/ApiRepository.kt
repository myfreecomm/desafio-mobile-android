package com.nexaas.challenge.domain.repository

import com.nexaas.challenge.domain.model.ProductDomain
import io.reactivex.Observable

interface ApiRepository {
    fun getProductsList(): Observable<ArrayList<ProductDomain>>
}