package br.com.mpc.android_challenge.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

abstract class ServiceAbstraction : CoroutineScope {

    fun <T> doRequest(call: suspend () -> Response<T>): Flow<Event<T>> = flow {

        launch {
            try {
                val response = withContext(Dispatchers.IO) { call.invoke() }
                if (response.isSuccessful) emit(Event(result = response.body()))
                else emit(Event(error = response.message()))
            } catch (e: Exception) {
                emit(Event(error = e.message))
            }
        }
    }

    class ResponseError(override val message: String?) : Exception()

    class Event<T>(
        val result: T? = null,
        val error: String? = null
    ) {
        var isSuccess = false
            get() = result != null
            private set

    }
}