package br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object Converters {

    @TypeConverter
    fun fromString(value: String?): Array<String> {
        val listType: Type = object : TypeToken<Array<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    private fun fromArrayList(list: Array<String?>?): String =
        Gson().toJson(list)
}


