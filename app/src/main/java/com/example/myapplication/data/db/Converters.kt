package com.example.myapplication.data.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromComicsList(value: List<String>): String {
        return value.joinToString { "," }
    }

    @TypeConverter
    fun toComicsList(value: String): List<String> {
        return value.split(",")
    }
}