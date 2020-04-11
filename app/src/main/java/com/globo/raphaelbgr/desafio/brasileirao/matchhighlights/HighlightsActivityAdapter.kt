package com.globo.raphaelbgr.desafio.brasileirao.matchhighlights

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globo.raphaelbgr.desafio.brasileirao.R
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Highlight
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.android.synthetic.main.rv_game_highlight.view.*

class HighlightsActivityAdapter(match: Match?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var highlightList: List<Highlight> = match?.matchHighlights!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MatchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_game_highlight, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return highlightList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_description.text = highlightList[position].description
        holder.itemView.tv_match_time.text = highlightList[position].matchTime
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
