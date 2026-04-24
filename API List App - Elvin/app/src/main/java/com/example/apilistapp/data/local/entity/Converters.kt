package com.example.apilistapp.data.local.entity

import androidx.room.TypeConverter
import com.example.apilistapp.data.remote.dto.Location
import com.example.apilistapp.data.remote.dto.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {


    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        return if (string.isEmpty()) emptyList() else string.split(",")
    }

    @TypeConverter
    fun fromLocation(location: Location): String {
        return "${location.name}|${location.url}"
    }

    @TypeConverter
    fun toLocation(string: String): Location {
        val parts = string.split("|")
        // parts[0] es el nombre, parts[1] es la url
        return Location(name = parts[0], url = parts[1])
    }

    @TypeConverter
    fun fromOrigin(origin: Origin): String {
        return "${origin.name}|${origin.url}"
    }

    @TypeConverter
    fun toOrigin(string: String): Origin {
        val parts = string.split("|")
        return Origin(name = parts[0], url = parts[1])
    }
}
