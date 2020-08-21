package com.example.challengeaccepted.feature.product.presentation.list

import androidx.lifecycle.liveData
import com.example.challengeaccepted.feature.product.domain.mapper.ProductListDataMapper
import com.example.challengeaccepted.feature.product.domain.usecase.ProductUseCase
import com.example.challengeaccepted.platform.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import org.koin.core.inject

class ProductListViewModel : BaseViewModel() {

    private val useCase: ProductUseCase by inject()

    fun productLD(fetchFromRemote: Boolean = false) = liveData<ProductListDataMapper> {
        useCase.getProducts(fetchFromRemote).collect {
            emit(it, this)
        }
    }

}