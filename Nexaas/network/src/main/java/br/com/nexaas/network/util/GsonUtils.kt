package br.com.nexaas.network.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> fromJson(json: String?): T? {
    val type = object : TypeToken<T>() {}.type
    return Gson().fromJson(json, type)
}