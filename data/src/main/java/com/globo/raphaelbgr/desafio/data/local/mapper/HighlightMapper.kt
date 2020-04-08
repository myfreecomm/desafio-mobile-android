package com.globo.raphaelbgr.desafio.data.local.mapper

import com.globo.raphaelbgr.desafio.data.local.entities.HighlightEntity
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Highlight
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match

class HighlightMapper {

    fun mapToModel(entities: List<HighlightEntity>): List<Highlight> {
        val list = ArrayList<Highlight>()
        for (highlight in entities) {
            list.add(mapToModel(highlight))
        }
        return list
    }

    private fun mapToModel(entity: HighlightEntity): Highlight {
        return Highlight(
            entity.id,
            entity.description,
            entity.favoredTeamId,
            entity.matchId,
            entity.matchTime,
            entity.type
        )
    }

    fun mapToEntity(matchList: List<Match>): List<HighlightEntity> {
        val list = ArrayList<HighlightEntity>()
        for (match in matchList) {
            if (match.matchHighlights != null) {
                for (highlight in match.matchHighlights) {
                    list.add(mapToEntity(highlight))
                }
            }
        }
        return HashSet(list).toList()
    }

    private fun mapToEntity(highlight: Highlight): HighlightEntity {
        return HighlightEntity(
            highlight.id,
            highlight.description,
            highlight.favoredTeamId,
            highlight.matchId,
            highlight.matchTime,
            highlight.type
        )
    }
}
