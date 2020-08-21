package com.example.challengeaccepted.platform.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

import java.io.IOException
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Arrays
import java.util.Date
import java.util.Locale

class GsonHelper {


    val gson: Gson
        get() = GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateSerializer())
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanTypeAdapter())
                .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerTypeAdapter())
                .registerTypeAdapter(Long::class.javaPrimitiveType, LongTypeAdapter())
                .registerTypeAdapter(Float::class.javaPrimitiveType, FloatTypeAdapter())
                .registerTypeAdapter(Double::class.javaPrimitiveType, DoubleTypeAdapter())
                .registerTypeAdapter(Int::class.java, IntegerTypeAdapter())
                .registerTypeAdapter(Long::class.java, LongTypeAdapter())
                .registerTypeAdapter(Float::class.java, FloatTypeAdapter())
                .registerTypeAdapter(Double::class.java, DoubleTypeAdapter())
                .registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
                .create()


    private inner class DateSerializer : JsonSerializer<Date> {

        override fun serialize(date: Date?, typfOfT: Type, context: JsonSerializationContext): JsonElement? {
            return if (date == null) null else JsonPrimitive(date.time)
        }
    }

    private inner class DateDeserializer : JsonDeserializer<Date> {

        @Throws(JsonParseException::class)
        override fun deserialize(jsonElement: JsonElement, typeOF: Type, context: JsonDeserializationContext): Date {
            for (format in DATE_FORMATS) {
                try {
                    return SimpleDateFormat(format, Locale.US).parse(jsonElement.asString)!!
                } catch (e: Exception) {
                }

            }

            try {
                return Date(jsonElement.asJsonPrimitive.asLong)
            } catch (e: Exception) {
            }

            throw JsonParseException("Unparseable date: \"" + jsonElement.asString + "\". Supported formats: " + Arrays.toString(
                DATE_FORMATS
            ))
        }
    }

    private inner class DoubleTypeAdapter : TypeAdapter<Double>() {
        @Throws(IOException::class)
        override fun write(jsonWriter: JsonWriter, value: Double?) {
            if (value == null) {
                jsonWriter.nullValue()
                return
            }
            jsonWriter.value(value)
        }

        @Throws(IOException::class)
        override fun read(jsonReader: JsonReader): Double? {
            val peek = jsonReader.peek()
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull()
                return null
            }
            if (peek == JsonToken.NUMBER) {
                return jsonReader.nextDouble()
            }

            try {
                val value = jsonReader.nextString()
                return if ("" == value) {
                    0.0
                } else java.lang.Double.parseDouble(value.replace(",", "."))
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }
    }

    private inner class IntegerTypeAdapter : TypeAdapter<Int>() {
        @Throws(IOException::class)
        override fun write(jsonWriter: JsonWriter, value: Int?) {
            if (value == null) {
                jsonWriter.nullValue()
                return
            }
            jsonWriter.value(value)
        }

        @Throws(IOException::class)
        override fun read(jsonReader: JsonReader): Int? {
            val peek = jsonReader.peek()
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull()
                return null
            }
            if (peek == JsonToken.NUMBER) {
                return jsonReader.nextInt()
            }

            try {
                val value = jsonReader.nextString()
                return if ("" == value) {
                    0
                } else Integer.parseInt(value)
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }
    }

    private inner class LongTypeAdapter : TypeAdapter<Long>() {
        @Throws(IOException::class)
        override fun write(jsonWriter: JsonWriter, value: Long?) {
            if (value == null) {
                jsonWriter.nullValue()
                return
            }
            jsonWriter.value(value)
        }

        @Throws(IOException::class)
        override fun read(jsonReader: JsonReader): Long? {
            val peek = jsonReader.peek()
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull()
                return null
            }
            if (peek == JsonToken.NUMBER) {
                return jsonReader.nextLong()
            }

            try {
                val value = jsonReader.nextString()
                return if ("" == value) {
                    0L
                } else java.lang.Long.parseLong(value)
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }
    }

    private inner class FloatTypeAdapter : TypeAdapter<Float>() {
        @Throws(IOException::class)
        override fun write(jsonWriter: JsonWriter, value: Float?) {
            if (value == null) {
                jsonWriter.nullValue()
                return
            }
            jsonWriter.value(value)
        }

        @Throws(IOException::class)
        override fun read(jsonReader: JsonReader): Float? {
            val peek = jsonReader.peek()
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull()
                return null
            }

            try {
                val value = jsonReader.nextString()
                return if ("" == value) {
                    0f
                } else java.lang.Float.parseFloat(value.replace(",", "."))
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }
    }

    private inner class BooleanTypeAdapter : TypeAdapter<Boolean>() {
        @Throws(IOException::class)
        override fun write(jsonWriter: JsonWriter, value: Boolean?) {
            if (value == null) {
                jsonWriter.nullValue()
                return
            }
            jsonWriter.value(value)
        }


        @Throws(IOException::class)
        override fun read(jsonReader: JsonReader): Boolean? {
            val peek = jsonReader.peek()
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull()
                return null
            }
            if (peek == JsonToken.BOOLEAN) {
                return jsonReader.nextBoolean()
            }

            try {
                val value = jsonReader.nextString()
                return if ("1" == value) {
                    true
                } else if ("0" == value) {
                    false
                } else {
                    java.lang.Boolean.parseBoolean(value)
                }
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }
    }

    companion object {

        private val DATE_FORMATS = arrayOf("yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd HH:mm",
                "yyyy-MM-dd",
                "dd-MM-yyyy HH:mm:ss",
                "dd/MM/yyyy HH:mm",
                "dd/MM/yyyy",
                "yyyy-MM-dd'T'HH:mm:ss.SSS",
                "yyyy-MM-dd'T'HH:mm:ss",
                "MMM dd, yyyy HH:mm:ss",
                "MMM dd, yyyy HH:mm:ss aaa")
    }


}
