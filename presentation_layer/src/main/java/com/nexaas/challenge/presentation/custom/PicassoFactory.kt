package com.nexaas.challenge.presentation.custom

import android.content.Context
import android.util.Log
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso


object PicassoFactory {

    private val TAG = "PICASSO"

    fun getPicasso(context: Context): Picasso {
        val builder = Picasso.Builder(context)
        builder.memoryCache(LruCache(context))
        val requestTransformer = Picasso.RequestTransformer { request ->
            Log.d(TAG, request.toString())
            request
        }
        builder.requestTransformer(requestTransformer)
//        builder.indicatorsEnabled(true)
        return builder.build()
    }

}