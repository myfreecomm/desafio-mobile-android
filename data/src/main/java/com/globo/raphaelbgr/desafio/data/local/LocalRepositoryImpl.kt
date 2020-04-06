package com.globo.raphaelbgr.desafio.data.local

import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

public class LocalRepositoryImpl: LocalRepository {
    override suspend fun loadMatchListAsync() = GlobalScope.async {
        ArrayList<Match>()
    }

}
