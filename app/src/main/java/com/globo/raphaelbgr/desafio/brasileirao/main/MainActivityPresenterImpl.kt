package com.globo.raphaelbgr.desafio.brasileirao.main

import com.globo.raphaelbgr.desafio.data.local.LocalRepository
import com.globo.raphaelbgr.desafio.data.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainActivityPresenterImpl(
    val api: ApiService,
    val local: LocalRepository
) : MainActivityPresenter {

    private lateinit var view: MainView

    override fun setView(view: MainView) {
        this.view = view
    }

    override fun getMatchList() {
//        val localMatches = local.loadMatchListAsync().await()
        getOnlineMatchList()
    }

    private fun getOnlineMatchList() {
        view.showLoading(true)
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val response = api.getMatchListAsync().await()
                try {
                    if (response.isSuccessful && response.body() != null && response.body()?.matchList?.isEmpty()!!) {
                        view.onMatchListApiEmpty()
                    } else if (response.isSuccessful && response.body() != null) {
                        view.onMatchListApiSuccess(response.body()?.matchList!!)
                    } else {
                        view.onMatchListApiFailure()
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    view.onMatchListApiFailure()
                } finally {
                    view.showLoading(false)
                }
            }
        }
    }
}