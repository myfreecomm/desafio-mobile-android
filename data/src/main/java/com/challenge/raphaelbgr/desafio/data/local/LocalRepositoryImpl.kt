package com.challenge.raphaelbgr.desafio.data.local

import com.challenge.raphaelbgr.desafio.data.local.mapper.HighlightMapper
import com.challenge.raphaelbgr.desafio.data.local.mapper.MatchMapper
import com.challenge.raphaelbgr.desafio.data.local.mapper.TeamMapper
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

class LocalRepositoryImpl(
    private val coroutineScope: CoroutineScope,
    private val db: AppDatabase
) : LocalRepository {
    override suspend fun loadMatchListAsync() = coroutineScope.async {
        val matches = MatchMapper().mapToModel(
            db.matchDao().fetchAll(),
            db.teamDao().fetchAll(),
            db.highlightDao().fetchAll()
        )
        matches
    }

    override suspend fun saveMatchesToDbAsync(list: List<Match>) = coroutineScope.async {
        val matchEntities = MatchMapper().mapToEntity(list)
        val teamEntities = TeamMapper().mapToEntity(list)
        val highLightEntities = HighlightMapper().mapToEntity(list)
        db.matchDao().insert(matchEntities)
        db.teamDao().insert(teamEntities)
        db.highlightDao().insert(highLightEntities)
    }

}
