package com.example.challengeaccepted.platform.base

import com.example.challengeaccepted.platform.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.koin.core.KoinComponent

open class BaseRepository<DATA> : KoinComponent{

    fun flow(flowFunc: () -> Flow<Resource<DATA>>) = flow<Resource<DATA>> {
        emitAll(flowFunc())
    }.onStart {
        emit(Resource.loading(true))
    }.onCompletion {
        emit(Resource.loading(false))
    }.flowOn(Dispatchers.IO)

}
