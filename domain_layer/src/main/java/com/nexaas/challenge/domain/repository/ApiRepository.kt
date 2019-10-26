package com.nexaas.challenge.domain.repository

import com.nexaas.challenge.domain.model.ProductDomain

interface ApiRepository {
    fun getProductsList(): ArrayList<ProductDomain>
}