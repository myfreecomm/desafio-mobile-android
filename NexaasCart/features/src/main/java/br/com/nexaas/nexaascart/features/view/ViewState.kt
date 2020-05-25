package br.com.nexaas.nexaascart.features.view

import br.com.nexaas.nexaascart.features.data.DataResponse

sealed class ViewState {
    object LoadingHome : ViewState()
    data class HomeViewState(val products: List<DataResponse>) : ViewState()
}

