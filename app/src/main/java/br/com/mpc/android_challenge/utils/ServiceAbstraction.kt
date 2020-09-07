package br.com.mpc.android_challenge.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

abstract class ServiceAbstraction {

    fun <T> doRequest(call: suspend () -> Response<T>): Flow<Event<T>> = flow {
        emit(Event<T>())

        emit(Event<T>().apply {
            try {
                val response = withContext(Dispatchers.IO) { call.invoke() }
                if (response.isSuccessful) content = response.body()
                else error = "Falha ao processar solicitação"
            } catch (e: Exception) {
                error = e.message
            } })
    }.flowOn(Dispatchers.IO)
}

class Event<C>{
    var isLoading = false
        get() = content == null && error == null
        private set

    var content: C? = null
    var error: String? = null
}