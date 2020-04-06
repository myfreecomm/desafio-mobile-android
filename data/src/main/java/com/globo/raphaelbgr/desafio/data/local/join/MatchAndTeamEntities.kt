package com.globo.raphaelbgr.desafio.data.local.join

import androidx.room.Embedded
import com.globo.raphaelbgr.desafio.data.local.entities.MatchEntity
import com.globo.raphaelbgr.desafio.data.local.entities.TeamEntity

class MatchAndTeamEntities {
    @Embedded
    lateinit var match: MatchEntity

    @Embedded
    lateinit var team: TeamEntity
}