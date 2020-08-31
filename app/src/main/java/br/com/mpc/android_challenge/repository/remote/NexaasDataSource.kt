package br.com.mpc.android_challenge.repository.remote

import br.com.mpc.android_challenge.repository.service.NexaasService
import br.com.mpc.android_challenge.utils.ServiceAbstraction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class NexaasDataSource(private val service: NexaasService) : ServiceAbstraction() {
    private val job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    fun getItems() = doRequest(call = { service.getItems() } )
}