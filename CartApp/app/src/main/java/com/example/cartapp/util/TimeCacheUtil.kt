package com.example.cartapp.util

import android.content.SharedPreferences

class TimeCacheUtil(private val timeSharedPreferences: SharedPreferences)  {
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L
    private var  CACHE_DURATION = "PrefsCacheDuration"

    fun checkCacheDuration(){
        val cacheTime  = timeSharedPreferences.getString(CACHE_DURATION, "")

        try{
            val cachePreferencesInt = cacheTime?.toInt() ?: 5 * 60
            updateRefreshTime(cachePreferencesInt.times(1000 * 1000 * 1000L))
        }
        catch (e: NumberFormatException){
            e.printStackTrace()
        }
    }

    fun updateRefreshTime(time: Long){
        refreshTime = time
    }

    fun getUpdateTime() : Long {
        return refreshTime
    }
}