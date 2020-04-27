package com.hotmail.fignunes.desafio_mobile.repository.remote.product.responses

enum class ProductErrorCodes(val code: Int) {
    UNAVAILABLE_SERVICE(404),
    ERROR_UNEXPECTED(500)
}