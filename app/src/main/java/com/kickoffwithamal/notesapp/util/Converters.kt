package com.kickoffwithamal.notesapp.util

import androidx.room.TypeConverter
import java.util.Date
import java.util.UUID

class Converters {

    // Converter for Date
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // Converter for UUID
    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun uuidFromString(uuid: String?): UUID? {
        return uuid?.let { UUID.fromString(it) }
    }
}