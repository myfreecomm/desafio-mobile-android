package com.challenge.raphaelbgr.desafio.brasileirao.main

import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Match

interface MainView {
    fun showLoading(show: Boolean)
    fun onMatchListApiSuccess(list: List<Match>)
    fun onMatchListApiFailure()
    fun onMatchListApiEmpty()
    fun onMatchListCacheSuccess(localMatches: List<Match>)
    fun onMatchListCacheEmpty()
}