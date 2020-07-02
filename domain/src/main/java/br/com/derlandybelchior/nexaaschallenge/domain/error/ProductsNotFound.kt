package br.com.derlandybelchior.nexaaschallenge.domain.error

object ProductsNotFound : Throwable(){
    override fun toString() = "No products found"
}