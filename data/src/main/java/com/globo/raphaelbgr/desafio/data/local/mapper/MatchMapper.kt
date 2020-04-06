package com.globo.raphaelbgr.desafio.data.local.mapper

import com.globo.raphaelbgr.desafio.data.local.entities.MatchEntity
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match

class MatchMapper {

    fun mapToModel(matchList: List<MatchEntity>) : List<Match> {
        val list = ArrayList<Match>()
        val entities = ArrayList<MatchEntity>()
        for (match in matchList) {

        }
        for (matchEntity in matchList) {
            list.add(Match(matchEntity))
        }
        return list
    }

    fun mapToEntity(matchList: List<Match>) : List<MatchEntity> {
        val list = ArrayList<MatchEntity>()
        for (match in matchList) {
            list.add(mapToEntity(match))
        }
        return list
    }

    private fun mapToEntity(match: Match): MatchEntity {
        return MatchEntity(match.id, match.matchDate,
            match.matchTeams?.home?.id,
            match.matchTeams?.home?.matchScore,
            match.matchTeams?.away?.id,
            match.matchTeams?.away?.matchScore)
    }
}
