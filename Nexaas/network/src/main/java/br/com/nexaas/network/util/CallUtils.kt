package br.com.nexaas.network.util

import br.com.nexaas.common.coroutines.ICoroutinesDispatcherProvider
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend inline fun <T> apiCall(
    dispatcher: ICoroutinesDispatcherProvider,
    crossinline block: suspend () -> T
): T {
    return withContext(dispatcher.io()) {
        try {
            block()
        } catch (error: Throwable) {
            throw when (error) {
                is HttpException -> {
                    // TODO parse error body to API exception
                    error
                }
                else -> {
                    error
                }
            }
        }
    }
}