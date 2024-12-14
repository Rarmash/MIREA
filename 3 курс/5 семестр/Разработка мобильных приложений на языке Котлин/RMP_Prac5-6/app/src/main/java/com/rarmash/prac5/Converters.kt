package com.rarmash.prac5

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringList(value: String): List<String>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}