package com.challenge.raphaelbgr.desafio.brasileirao.util

import android.webkit.URLUtil
import android.widget.ImageView
import com.challenge.raphaelbgr.desafio.brasileirao.BuildConfig
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.MatchTeams
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class BrasileiraoUtil {

    companion object {

        fun parseDate(date: Date?): String? {
            return if (date != null) SimpleDateFormat(
                "dd/MM/yy HH:mm",
                Locale.getDefault()
            ).format(date)
            else {
                null
            }
        }

        fun getMatchName(matchTeams: MatchTeams?): String {
            val homeName = matchTeams?.home?.teamName
            val awayName = matchTeams?.away?.teamName

            return if (homeName != null && awayName != null && homeName.length >= 3 && awayName.length >= 3)
                "${homeName.substring(0, 3)
                    .toUpperCase(Locale.getDefault())} x ${awayName.substring(0, 3)
                    .toUpperCase(Locale.getDefault())}"
            else "$homeName x $awayName".toUpperCase(Locale.getDefault())
        }

        fun getMatchNameHighlights(matchTeams: MatchTeams?, suffix: String): String {
            val homeName = matchTeams?.home?.teamName
            val awayName = matchTeams?.away?.teamName

            return if (homeName != null && awayName != null && homeName.length >= 3 && awayName.length >= 3)
                "${homeName.substring(0, 3)
                    .toUpperCase(Locale.getDefault())} x ${awayName.substring(0, 3)
                    .toUpperCase(Locale.getDefault())} - $suffix"
            else "$homeName x ${awayName?.toUpperCase(Locale.getDefault())} - $suffix"
        }

        fun loadImage(imageView: ImageView, url: String?) {
            if (!URLUtil.isValidUrl(url))
                return
            val picasso = Picasso.get()
            picasso.setIndicatorsEnabled(BuildConfig.DEBUG)
            picasso.isLoggingEnabled = BuildConfig.DEBUG
            picasso.load(url)
                .fit()
                .error(com.challenge.raphaelbgr.desafio.brasileirao.R.drawable.logo_camp_bras)
                .placeholder(com.challenge.raphaelbgr.desafio.brasileirao.R.drawable.logo_camp_bras)
                .into(imageView)
        }
    }
}