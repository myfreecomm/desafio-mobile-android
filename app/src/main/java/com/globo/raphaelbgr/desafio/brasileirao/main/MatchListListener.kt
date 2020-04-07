package com.globo.raphaelbgr.desafio.brasileirao.main

import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match

interface MatchListListener {
    fun onMatchClick(match: Match)
}
