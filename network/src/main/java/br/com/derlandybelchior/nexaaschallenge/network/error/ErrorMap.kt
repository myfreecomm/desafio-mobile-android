package br.com.derlandybelchior.nexaaschallenge.network.error

import br.com.derlandybelchior.nexaaschallenge.domain.error.ErrorMapping
import br.com.derlandybelchior.nexaaschallenge.domain.error.NetworkError
import retrofit2.HttpException
import java.net.SocketTimeoutException

class ErrorMap : ErrorMapping {
    override fun map(e: Throwable): Throwable {
        return when(e) {
            is SocketTimeoutException -> NetworkError.TimeoutError
            is HttpException -> mapHttpException(e)
            else -> e
        }
    }

    private fun mapHttpException(e: HttpException): Throwable {
        return when(e.code()) {
            in 400..499 -> NetworkError.HostError
            in 500..599 -> NetworkError.RemoteIntegrationError
            else -> e
        }
    }
}