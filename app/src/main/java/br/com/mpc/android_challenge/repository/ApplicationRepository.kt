package br.com.mpc.android_challenge.repository

import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.repository.local.LocalDataSource
import br.com.mpc.android_challenge.repository.remote.NexaasDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ApplicationRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: NexaasDataSource
) {
    private var hasCache: Boolean = false

    fun getItems(): Flow<ArrayList<Item>?> {
        return if (hasCache) {
            localDataSource.getItems()
        } else {
            remoteDataSource.getItems()
                .map { return@map if (it.isSuccess) it.result else null }
        }
    }
}