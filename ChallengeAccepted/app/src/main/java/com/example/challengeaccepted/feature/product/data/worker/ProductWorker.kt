package com.example.challengeaccepted.feature.product.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.challengeaccepted.feature.product.data.api.ProductRepository
import kotlinx.coroutines.flow.collect
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductWorker(
    context: Context,
    workerParams: WorkerParameters
) :
    CoroutineWorker(context, workerParams), KoinComponent {

    companion object {
        const val TAG_WORKER = "ProductWorkerTag"
    }

    private val repo : ProductRepository by inject()

    override suspend fun doWork(): Result {
        repo.getProducts().collect()
        return Result.success()
    }
}