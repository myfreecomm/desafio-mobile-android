package com.nexaas.challenge.domain.model

import com.nexaas.challenge.domain.core.DomainModel

data class ProductDomain (val name: String?,
                          val quantity: Number?,
                          val stock: Number?,
                          val imageUrl: String?,
                          val price: Number?,
                          val tax: Number?,
                          val shipping: Number?,
                          val description: String?): DomainModel()