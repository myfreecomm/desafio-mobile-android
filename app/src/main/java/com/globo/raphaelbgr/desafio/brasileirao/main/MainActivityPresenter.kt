package com.globo.raphaelbgr.desafio.brasileirao.main

interface MainActivityPresenter {
    fun getMatchList()
    fun setView(view: MainView)
    fun getOnlineMatchListForced()
}