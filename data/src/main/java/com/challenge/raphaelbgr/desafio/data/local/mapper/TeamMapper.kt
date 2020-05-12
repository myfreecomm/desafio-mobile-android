package com.challenge.raphaelbgr.desafio.data.local.mapper

import com.challenge.raphaelbgr.desafio.data.local.entities.TeamEntity
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Match
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Team

class TeamMapper {

    fun mapToEntity(matchList: List<Match>): List<TeamEntity> {
        val list = ArrayList<TeamEntity>()
        for (match in matchList) {
            list.add(mapToEntity(match.matchTeams?.home!!))
            list.add(mapToEntity(match.matchTeams.away!!))
        }
        return HashSet(list).toList()
    }

    private fun <T> mapToEntity(team: T): TeamEntity where T : Team {
        return TeamEntity(
            team.id,
            team.teamName,
            team.teamShield
        )
    }
}
