package com.globo.raphaelbgr.desafio.data.local

import com.globo.raphaelbgr.desafio.data.local.mapper.MatchMapper
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

public class LocalRepositoryImpl(private val db: AppDatabase) : LocalRepository {
    override suspend fun loadMatchListAsync() = GlobalScope.async {
//        MatchMapper().map(appDatabase.matchDao().getMatchList())
        ArrayList<Match>()
    }

    override suspend fun saveMatchesToDbAsync(list: List<Match>) = GlobalScope.async {
        db.matchDao().insert(MatchMapper().mapToEntity(list))
    }

}
