package com.globo.raphaelbgr.desafio.brasileirao.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globo.raphaelbgr.desafio.brasileirao.R

class MainActivityAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GameViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_game_item, parent, false))
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
