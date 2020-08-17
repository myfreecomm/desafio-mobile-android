package com.example.challengeaccepted.feature.product.presentation.list.domain

import com.example.challengeaccepted.feature.product.domain.mapper.ProductListDataMapper
import com.example.challengeaccepted.platform.base.BaseNavigator

interface ProductListNavigator : BaseNavigator {

    fun showProduts(products: ProductListDataMapper)

}