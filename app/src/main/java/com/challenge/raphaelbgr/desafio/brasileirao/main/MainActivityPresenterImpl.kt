package com.challenge.raphaelbgr.desafio.brasileirao.main

import com.challenge.raphaelbgr.desafio.brasileirao.base.BasePresenter
import com.challenge.raphaelbgr.desafio.data.local.LocalRepository
import com.challenge.raphaelbgr.desafio.data.network.ApiService
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainActivityPresenterImpl(
    val api: ApiService,
    val local: LocalRepository
) : MainActivityPresenter, BasePresenter() {

    private lateinit var view: MainView

    override fun setView(view: MainView) {
        this.view = view
    }

    override fun getMatchList() {
        view.showLoading(true)
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                val localMatches = local.loadMatchListAsync().await()
                if (!localMatches.isNullOrEmpty()) {
                    view.onMatchListCacheSuccess(localMatches)
                    view.showLoading(false)
                } else {
                    getOnlineMatchList()
                }
            }
        }
    }

    override fun getOnlineMatchListForced() {
        view.showLoading(true)
        getOnlineMatchList()
    }

    private fun getOnlineMatchList() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    val response = api.getMatchListAsync().await()
                    if (response.isSuccessful && response.body() != null && response.body()?.matchList?.isEmpty()!!) {
                        view.onMatchListApiEmpty()
                    } else if (response.isSuccessful && response.body() != null) {
                        response.body()!!.matchList?.let { saveDataToDb(it) }
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

    private fun saveDataToDb(list: List<Match>) {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                local.saveMatchesToDbAsync(list).await()
            }
        }
    }
}