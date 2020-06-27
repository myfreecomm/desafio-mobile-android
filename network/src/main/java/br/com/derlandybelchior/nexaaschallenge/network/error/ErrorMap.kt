package br.com.derlandybelchior.nexaaschallenge.network.error

import br.com.derlandybelchior.nexaaschallenge.domain.error.ErrorMapping
import br.com.derlandybelchior.nexaaschallenge.domain.error.NetworkError
import java.net.SocketTimeoutException

class ErrorMap : ErrorMapping {
    override fun map(e: Throwable): Throwable {
        return when(e) {
            is SocketTimeoutException -> NetworkError.TimeoutError
            else -> e
        }
    }
}