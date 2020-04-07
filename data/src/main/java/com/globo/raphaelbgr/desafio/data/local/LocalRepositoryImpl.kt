package com.globo.raphaelbgr.desafio.data.local

import com.globo.raphaelbgr.desafio.data.local.mapper.MatchMapper
import com.globo.raphaelbgr.desafio.data.local.mapper.TeamMapper
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LocalRepositoryImpl(private val db: AppDatabase) : LocalRepository {
    override suspend fun loadMatchListAsync() = GlobalScope.async {
        val matches = MatchMapper().mapToModel(db.matchDao().fetchAll(), db.teamDao().fetchAll())
        matches
    }

    override suspend fun saveMatchesToDbAsync(list: List<Match>) = GlobalScope.async {
        val matchEntities = MatchMapper().mapToEntity(list)
        val teamEntities = TeamMapper().mapToEntity(list)
        db.matchDao().insert(matchEntities)
        db.teamDao().insert(teamEntities)
    }

}
