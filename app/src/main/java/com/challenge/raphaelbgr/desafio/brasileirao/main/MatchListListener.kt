package com.challenge.raphaelbgr.desafio.brasileirao.main

import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Match

interface MatchListListener {
    fun onMatchClick(match: Match)
}
