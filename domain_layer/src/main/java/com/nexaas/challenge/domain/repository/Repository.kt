package com.nexaas.challenge.domain.repository

interface Repository {
    fun onRequestSuccess(tag: String, obj: String)
    fun onRequestError(tag: String, e: Throwable)
}