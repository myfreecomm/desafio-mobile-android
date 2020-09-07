package br.com.mpc.android_challenge.repository.remote

import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.repository.service.NexaasService
import br.com.mpc.android_challenge.utils.Event
import br.com.mpc.android_challenge.utils.ServiceAbstraction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class NexaasDataSource(private val service: NexaasService) : ServiceAbstraction() {

    suspend fun getItems(): Flow<Event<ArrayList<Item>>> = doRequest(call = { service.getItems() } ).map { return@map it }
}