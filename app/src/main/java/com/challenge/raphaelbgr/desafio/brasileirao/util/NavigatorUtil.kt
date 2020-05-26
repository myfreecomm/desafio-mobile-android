package com.challenge.raphaelbgr.desafio.brasileirao.util

import android.content.Context
import android.content.Intent
import com.challenge.raphaelbgr.desafio.brasileirao.matchdetails.MatchDetailsActivity
import com.challenge.raphaelbgr.desafio.brasileirao.matchdetails.MatchDetailsActivity.Companion.MATCH_PARAM
import com.challenge.raphaelbgr.desafio.brasileirao.matchhighlights.MatchHighlightsActivity
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Match

class NavigatorUtil(private val context: Context) {

    fun navigateToMatchDetails(match: Match) {
        val intent = Intent(context, MatchDetailsActivity::class.java)
        intent.putExtra(MATCH_PARAM, match)
        context.startActivity(intent)
    }

    fun navigateToMatchHighlights(match: Match) {
        val intent = Intent(context, MatchHighlightsActivity::class.java)
        intent.putExtra(MATCH_PARAM, match)
        context.startActivity(intent)
    }
}