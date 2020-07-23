package com.example.nexaaschallenge.repo

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.nexaaschallenge.BuildConfig
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object Reachability {

    private val TAG = "Reachability"

    private fun isNetworkAvailable(context: Context): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        Log.d(TAG, "isNetworkAvailable: ${(network != null)}")
        return (network?.isConnected) ?: false
    }

    fun isInternetConnected(context: Context): Boolean {
        if (isNetworkAvailable(context)) {
            try {
                val connection = URL(BuildConfig.BASE_URL + BuildConfig.PATH_URL).openConnection() as HttpURLConnection
                connection.apply {
                    setRequestProperty("User-Agent", "Test")
                    setRequestProperty("Connection", "close")
                    connectTimeout = 100 // configurable
                    connect()
                    Log.d(TAG, "isInternetConnected: ${(responseCode == 200)}")
                    return (responseCode == 200)
                }
            } catch (e: IOException) {
                Log.e(TAG, "Error checking internet connection", e)
            }
        } else {
            Log.w(TAG, "No network available!")
        }
        Log.d(TAG, "isInternetConnected: false")
        return false
    }
}