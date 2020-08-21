package com.example.challengeaccepted.platform.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(
            oldItem: T,
            newItem: T
    ): Boolean {
        return compare(oldItem, newItem)
    }

    override fun areContentsTheSame(
            oldItem: T,
            newItem: T
    ): Boolean {
        return compare(oldItem, newItem)
    }

    private fun compare(oldItem: T, newItem: T) = oldItem == newItem
}
