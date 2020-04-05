package com.globo.raphaelbgr.desafio.brasileirao.main

interface MainView {
    fun showLoading(show: Boolean)
    fun onGamesListApiSuccess()
    fun onGamesListApiFailure()
    fun onGamesListCacheSuccess()
    fun onGamesListCacheEmpty()
}