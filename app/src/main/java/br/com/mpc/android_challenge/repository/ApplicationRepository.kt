package br.com.mpc.android_challenge.repository

import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.repository.local.LocalDataSource
import br.com.mpc.android_challenge.repository.remote.NexaasDataSource
import br.com.mpc.android_challenge.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class ApplicationRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: NexaasDataSource
) {
    private var hasCache: Boolean = false

    suspend fun getItems(): Flow<Event<ArrayList<Item>>> {
        return if (hasCache) localDataSource.getItems()
        else {
            val itemsFlow = remoteDataSource.getItems()
            val items: ArrayList<Item>? = itemsFlow.reduce { _, value -> value }.content
            if (items != null) {
                withContext(Dispatchers.IO){
                    localDataSource.deleteItems()
                    localDataSource.setItems(items.toList())
                }
                hasCache = true
            }
            itemsFlow
        }
    }
}