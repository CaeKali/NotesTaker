package com.example.notestaker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity
@TypeConverters(Converters::class)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long,
    val noteTitle: String,
    val noteBody: String,
    val editedDate: Date
)