package com.valderas.leonardo.nexaasdesafio.main.mvvm.view.pull

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valderas.leonardo.nexaasdesafio.databinding.PullItemBinding
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Contributor

class PullRequestAdapter(private var pullList: MutableList<Contributor>,
                         private var listener: OnItemClickListener)
    : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PullItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(pullList[position], listener)

    override fun getItemCount(): Int = pullList.size

    fun replaceItems(pullList: MutableList<Contributor>) {
        this.pullList = pullList
        notifyDataSetChanged()
    }

    fun rangeItems(newsList: MutableList<Contributor>) {
        this.pullList.addAll(newsList)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(url: String, view: View)
    }

    class ViewHolder(private var binding: PullItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(contributor: Contributor, listener: OnItemClickListener?) {
            binding.model = contributor
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(contributor.url, binding.root) }
            }

            binding.executePendingBindings()
        }
    }
}