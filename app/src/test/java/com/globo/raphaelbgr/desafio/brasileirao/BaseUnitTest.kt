package com.globo.raphaelbgr.desafio.brasileirao

import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.coroutines.CoroutineContext

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
open class BaseUnitTest {

    val testDispatcher = TestCoroutineDispatcher()
    val managedCoroutineScope: ManagedCoroutineScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}

interface ManagedCoroutineScope : CoroutineScope {
    abstract fun launch(block: suspend CoroutineScope.() -> Unit): Job
}

@ExperimentalCoroutinesApi
class TestScope(override val coroutineContext: CoroutineContext) : ManagedCoroutineScope {
    private val scope = TestCoroutineScope(coroutineContext)
    override fun launch(block: suspend CoroutineScope.() -> Unit): Job {
        return scope.launch {
            block.invoke(this)
        }
    }
}
