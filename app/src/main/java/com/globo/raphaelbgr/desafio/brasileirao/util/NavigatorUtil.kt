package com.globo.raphaelbgr.desafio.brasileirao.util

import android.content.Context
import android.content.Intent
import com.globo.raphaelbgr.desafio.brasileirao.matchdetails.MatchDetails
import com.globo.raphaelbgr.desafio.brasileirao.matchdetails.MatchDetails.Companion.MATCH_PARAM
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match

class NavigatorUtil(private val context: Context) {

    fun navigateToMatchDetails(match: Match) {
        val intent = Intent(context, MatchDetails::class.java)
        intent.putExtra(MATCH_PARAM, match)
        context.startActivity(intent)
    }
}