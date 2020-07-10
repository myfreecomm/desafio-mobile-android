package br.com.nexaas.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface ICoroutinesDispatcherProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
    fun computation(): CoroutineDispatcher
}