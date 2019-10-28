package com.nexaas.challenge.domain.repository

import com.nexaas.challenge.domain.model.ProductDomain
import io.reactivex.Single

interface ApiRepository: Repository {
    fun getProductsList(): Single<List<ProductDomain>>
}