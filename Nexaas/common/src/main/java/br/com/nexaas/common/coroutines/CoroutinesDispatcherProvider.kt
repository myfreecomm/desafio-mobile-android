package br.com.nexaas.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutinesDispatcherProvider : ICoroutinesDispatcherProvider {
    override fun io(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    override fun ui(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    override fun computation(): CoroutineDispatcher {
        return Dispatchers.Default
    }

}