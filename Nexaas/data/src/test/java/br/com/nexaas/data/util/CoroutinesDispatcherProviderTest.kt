package br.com.nexaas.data.util

import br.com.nexaas.common.coroutines.ICoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class CoroutinesDispatcherProviderTest(
    private val dispatcher : TestCoroutineDispatcher = TestCoroutineDispatcher()
) : ICoroutinesDispatcherProvider {

    override fun io(): CoroutineDispatcher {
        return dispatcher
    }

    override fun ui(): CoroutineDispatcher {
        return dispatcher
    }

    override fun computation(): CoroutineDispatcher {
        return dispatcher
    }

}