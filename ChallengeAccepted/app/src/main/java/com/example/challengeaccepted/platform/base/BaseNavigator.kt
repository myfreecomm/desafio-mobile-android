package com.example.challengeaccepted.platform.base

interface BaseNavigator {
    fun showLoading(isShimmer: Boolean = false)
    fun hideLoading(isShimmer: Boolean = false)
    fun showErrorMessage(string: String? = null)
}