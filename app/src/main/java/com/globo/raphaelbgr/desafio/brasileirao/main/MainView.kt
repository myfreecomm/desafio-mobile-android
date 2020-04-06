package com.globo.raphaelbgr.desafio.brasileirao.main

import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match

interface MainView {
    fun showLoading(show: Boolean)
    fun onMatchListApiSuccess(list: List<Match>)
    fun onMatchListApiFailure()
    fun onMatchListApiEmpty()
    fun onMatchListCacheSuccess()
    fun onMatchListCacheEmpty()
}