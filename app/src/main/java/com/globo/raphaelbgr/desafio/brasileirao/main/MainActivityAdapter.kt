package com.globo.raphaelbgr.desafio.brasileirao.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.globo.raphaelbgr.desafio.brasileirao.BuildConfig
import com.globo.raphaelbgr.desafio.brasileirao.R
import com.globo.raphaelbgr.desafio.brasileirao.util.BrasileiraoUtil
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_game_item.view.*

class MainActivityAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: List<Match> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MatchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_game_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_home_team_name.text = list[position].matchTeams?.home?.teamName
        holder.itemView.tv_away_team_name.text = list[position].matchTeams?.away?.teamName
        holder.itemView.tv_home_team_score.text =
            list[position].matchTeams?.home?.matchScore.toString()
        holder.itemView.tv_away_team_score.text =
            list[position].matchTeams?.away?.matchScore.toString()
        holder.itemView.tv_match_date.text = BrasileiraoUtil.parseDate(list[position].matchDate)

        loadShieldImage(
            holder.itemView.iv_home_team_shield,
            list[position].matchTeams?.home?.teamShield
        )
        loadShieldImage(
            holder.itemView.iv_away_team_shield,
            list[position].matchTeams?.away?.teamShield
        )
    }

    private fun loadShieldImage(imageView: ImageView, url: String?) {
        if (!URLUtil.isValidUrl(url))
            return
        val picasso = Picasso.get()
        picasso.setIndicatorsEnabled(BuildConfig.DEBUG)
        picasso.isLoggingEnabled = BuildConfig.DEBUG
        picasso.load(url)
            .fit()
            .error(R.drawable.logo_camp_bras)
            .placeholder(R.drawable.logo_camp_bras)
            .into(imageView)
    }

    fun setMatchList(list: List<Match>) {
        this.list = list
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
