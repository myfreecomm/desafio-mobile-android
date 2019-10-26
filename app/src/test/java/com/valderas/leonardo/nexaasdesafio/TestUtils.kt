package com.valderas.leonardo.nexaasdesafio

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.StringReader

object TestUtils {
    fun getFileString(path: String): String {
        try {
            val sb = StringBuilder()
            val readerStream = BufferedReader(InputStreamReader(this.javaClass::class.java.getResourceAsStream(path)))
            var line: String? = ""
            while (line != null) {
                line = readerStream.readLine()
                sb.append(line)
            }

            return sb.toString()
        } catch (e: IOException) {
            throw IllegalArgumentException("Could not read from resource at: $path")
        }
    }

    fun <T> getGson(json: String): T {
        val gson = Gson()
        val reader = JsonReader(StringReader(json))
        reader.isLenient = true
        val listType = object : TypeToken<T>() {}.type
        return gson.fromJson(reader, listType) as T
    }
}