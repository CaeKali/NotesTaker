package com.example.notestaker.data.local

import androidx.room.TypeConverter
import java.util.Date

public class Converters {
    @TypeConverter
    public fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    public fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}