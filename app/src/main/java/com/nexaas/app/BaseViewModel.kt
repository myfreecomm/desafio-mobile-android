package com.nexaas.app

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel :
    ViewModel(), CoroutineScope {
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    suspend fun <T> execute(block: suspend () -> T): T {
        return withContext(coroutineContext) {
            return@withContext block()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
