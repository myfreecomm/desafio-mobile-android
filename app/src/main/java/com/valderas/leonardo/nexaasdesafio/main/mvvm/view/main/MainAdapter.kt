package com.valderas.leonardo.nexaasdesafio.main.mvvm.view.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valderas.leonardo.nexaasdesafio.databinding.RepoItemBinding
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Repo

class MainAdapter(private var reposList: MutableList<Repo>,
                  private var listener: OnItemClickListener)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepoItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(reposList[position], listener)

    override fun getItemCount(): Int = reposList.size

    fun replaceItems(reposList: MutableList<Repo>) {
        this.reposList = reposList
        notifyDataSetChanged()
    }

    fun rangeItems(newsList: MutableList<Repo>) {
        this.reposList.addAll(newsList)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(repo: Repo, view: View)
    }

    class ViewHolder(private var binding: RepoItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repo, listener: OnItemClickListener?) {
            binding.model = repo
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(repo, binding.root) }
            }

            binding.executePendingBindings()
        }
    }
}