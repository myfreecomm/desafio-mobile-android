package com.example.challengeaccepted.feature.product.presentation.list.presentation

import android.os.Handler
import com.example.challengeaccepted.feature.product.presentation.list.domain.ProductUseCase
import com.example.challengeaccepted.feature.product.presentation.list.domain.ProductListNavigator
import com.example.challengeaccepted.platform.base.BaseViewModel
import com.example.challengeaccepted.platform.network.Response
import org.koin.core.inject

class ProductListViewModel : BaseViewModel<ProductListNavigator>() {

    private val useCase: ProductUseCase by inject()

    //loading delayed just to see the Shimmer working
    fun loadOrders() {
        useCase.getProducts(
            Response(
                subscribe = { data ->
                    Handler().postDelayed({
                        if (data != null) {
                            getNavigator().showProduts(data)
                        }
                    }, 2000)
                },
                onSubscribe = { getNavigator().showLoading(true) },
                onTerminate = {
                    Handler().postDelayed({
                        getNavigator().hideLoading(true)
                    }, 2000)
                },
                error = { getNavigator().showErrorMessage(it.message) })
        )
    }
}