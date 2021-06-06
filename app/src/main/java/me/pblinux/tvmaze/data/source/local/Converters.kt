package me.pblinux.tvmaze.data.source.local

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromStringToStringList(json: String): List<String> {
        return Json { ignoreUnknownKeys = true }.decodeFromString(json)
    }

    @TypeConverter
    fun fromStringListToString(list: List<String>): String {
        return Json { ignoreUnknownKeys = true }.encodeToString(list)
    }
}
