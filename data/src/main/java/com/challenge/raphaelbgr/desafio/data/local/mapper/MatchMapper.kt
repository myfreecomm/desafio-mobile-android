package com.challenge.raphaelbgr.desafio.data.local.mapper

import com.challenge.raphaelbgr.desafio.data.local.entities.HighlightEntity
import com.challenge.raphaelbgr.desafio.data.local.entities.MatchEntity
import com.challenge.raphaelbgr.desafio.data.local.entities.TeamEntity
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Away
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Home
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Match
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.MatchTeams

class MatchMapper {

    /**
     * Gets the two entity lists and reassemble the object for andorid layer to work with.
     */
    fun mapToModel(
        matchEntityList: List<MatchEntity>,
        teamList: List<TeamEntity>,
        highLightList: List<HighlightEntity>
    ): List<Match> {
        val list = ArrayList<Match>()
        for (matchEntity in matchEntityList) {
            if (teamList.filter { it.id == matchEntity.awayTeamId }.none()) continue
            list.add(
                Match(
                    matchEntity.id,
                    matchEntity.matchDate,
                    MatchTeams(
                        Away(
                            matchEntity.awayTeamId,
                            matchEntity.awayTeamScore,
                            teamList.filter { it.id == matchEntity.awayTeamId }[0].teamName,
                            teamList.filter { it.id == matchEntity.awayTeamId }[0].teamShield
                        ),
                        Home(
                            matchEntity.homeTeamId,
                            matchEntity.homeTeamScore,
                            teamList.filter { it.id == matchEntity.homeTeamId }[0].teamName,
                            teamList.filter { it.id == matchEntity.homeTeamId }[0].teamShield
                        )
                    ),
                    matchEntity.matchPlace,
                    HighlightMapper().mapToModel(highLightList)
                )
            )
        }
        return list
    }

    fun mapToEntity(matchList: List<Match>): List<MatchEntity> {
        val list = ArrayList<MatchEntity>()
        for (match in matchList) {
            list.add(mapToEntity(match))
        }
        return list
    }

    private fun mapToEntity(match: Match): MatchEntity {
        return MatchEntity(
            match.id,
            match.matchDate,
            match.matchPlace,
            match.matchTeams?.home?.id,
            match.matchTeams?.home?.matchScore,
            match.matchTeams?.away?.id,
            match.matchTeams?.away?.matchScore
        )
    }
}
