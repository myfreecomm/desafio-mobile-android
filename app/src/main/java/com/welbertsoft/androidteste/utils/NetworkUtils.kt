package com.welbertsoft.androidteste.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo


/**
Created By Welbert Moreira on 24/06/2020 : 22:33
 */
class NetworkUtils {

    companion object {
        @JvmStatic
        fun isConnected(context: Context): Boolean {
            var info: NetworkInfo? = null
            try {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                info = cm.activeNetworkInfo
            } catch (e: Exception) {
            }
            return info != null && info.isConnected && info.type == NetworkCapabilities.TRANSPORT_CELLULAR
        }
    }
}