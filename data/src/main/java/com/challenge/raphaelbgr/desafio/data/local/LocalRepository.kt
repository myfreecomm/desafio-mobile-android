package com.challenge.raphaelbgr.desafio.data.local

import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.coroutines.Deferred

interface LocalRepository {
    suspend fun loadMatchListAsync(): Deferred<List<Match>>
    suspend fun saveMatchesToDbAsync(list: List<Match>): Deferred<Unit>
}