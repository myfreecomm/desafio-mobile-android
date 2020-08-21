package com.example.challengeaccepted.platform.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengeaccepted.platform.data.network.Resource
import com.example.challengeaccepted.platform.data.network.State
import com.example.challengeaccepted.platform.util.CustomMessage
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {

    private val _showMessageLD = MutableLiveData<CustomMessage>()
    val showMessageLD: LiveData<CustomMessage> = _showMessageLD

    private val _loadingLD = MutableLiveData<Boolean>()
    val loadingLD: LiveData<Boolean> = _loadingLD

    protected suspend fun <T> emit(
        resource: Resource<T>,
        liveDataScope: LiveDataScope<T>
    ) {
        whenIsResourceSuccess(resource) {
            resource.data?.let { liveDataScope.emit(it) }
        }
    }

    private suspend fun <T> whenIsResourceSuccess(resource: Resource<T>?, success: suspend () -> Unit) {
        if(resource != null) {
            when (resource.state) {
                is State.Loading -> {
                    _loadingLD.value = resource.state.isLoading
                }
                is State.Error -> {
                    _showMessageLD.value = CustomMessage.error(resource.state.exception?.msg)
                }
                else -> {
                    success()
                }
            }
        }
    }
}