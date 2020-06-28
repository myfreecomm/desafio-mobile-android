package br.com.derlandybelchior.nexaaschallenge.domain.error

sealed class NetworkError : Throwable(){
    object HostError : NetworkError()
    object TimeoutError : NetworkError()
    object RemoteIntegrationError : NetworkError()
}