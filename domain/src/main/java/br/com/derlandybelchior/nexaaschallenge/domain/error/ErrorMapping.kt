package br.com.derlandybelchior.nexaaschallenge.domain.error

interface ErrorMapping {
    fun map(e: Throwable) : Throwable
}