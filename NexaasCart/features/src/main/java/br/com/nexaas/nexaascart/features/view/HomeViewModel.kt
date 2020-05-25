package br.com.nexaas.nexaascart.features.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.nexaas.datalayer.SafeResponse
import br.com.nexaas.datalayer.safeRequest
import br.com.nexaas.nexaascart.features.data.DataResponse
import br.com.nexaas.nexaascart.features.domain.GetHomeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val getHomeUseCase: GetHomeUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.Main + SupervisorJob()
): ViewModel() {

    private val mutableViewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = mutableViewState

    private var coroutineScope = CoroutineScope(dispatcher)

    fun loadProductsHome() {
        ViewState.LoadingHome.run()
        coroutineScope.launch {
            when(val response = safeRequest { getHomeUseCase() }) {
                is SafeResponse.Success -> loadSuccess(response.value)
            }
        }
    }

    private fun loadSuccess(value: List<DataResponse>) {
        ViewState.HomeViewState(value).run()
    }

    private fun ViewState.run() {
        mutableViewState.postValue(this)
    }
}