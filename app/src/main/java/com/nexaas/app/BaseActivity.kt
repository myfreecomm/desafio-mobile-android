package com.nexaas.app

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity(layout: Int) : AppCompatActivity(layout),
    CoroutineScope, KoinComponent {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    suspend fun <T> execute(block: suspend () -> T): T {
        return withContext(coroutineContext) {
            return@withContext block()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancelChildren()
    }
}
