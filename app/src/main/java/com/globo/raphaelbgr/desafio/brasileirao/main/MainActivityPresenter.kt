package com.globo.raphaelbgr.desafio.brasileirao.main

import com.globo.raphaelbgr.desafio.model.matchlist.Match

interface MainActivityPresenter {
    fun getMatchesList(): List<Match>
}