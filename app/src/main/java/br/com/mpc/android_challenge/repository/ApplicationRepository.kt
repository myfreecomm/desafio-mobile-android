package br.com.mpc.android_challenge.repository

import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.repository.local.LocalDataSource
import br.com.mpc.android_challenge.repository.remote.NexaasDataSource
import br.com.mpc.android_challenge.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import timber.log.Timber

class ApplicationRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: NexaasDataSource
) {
    private var hasCache: Boolean = false

    suspend fun getItems(): Flow<Event<ArrayList<Item>>> {
        return if (hasCache) localDataSource.getItems().flowOn(Dispatchers.IO)
        else {
            val itemsFlow = remoteDataSource.getItems()
            val items: ArrayList<Item>? = itemsFlow.reduce { _, value -> value }.content
            if (items != null) {
                hasCache = true
                withContext(Dispatchers.IO){
                    localDataSource.setItems(items.toList())
                }
            }
            itemsFlow
        }
    }
}