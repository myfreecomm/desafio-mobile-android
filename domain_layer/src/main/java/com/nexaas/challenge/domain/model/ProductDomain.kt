package com.nexaas.challenge.domain.model

import com.nexaas.challenge.domain.core.DomainModel

data class ProductDomain (val name: String?,
                          val quantity: Int?,
                          val stock: Int?,
                          val imageUrl: String?,
                          val price: Double?,
                          val tax: Double?,
                          val shipping: Double?,
                          val description: String?): DomainModel()