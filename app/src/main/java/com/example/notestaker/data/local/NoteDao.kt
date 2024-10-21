package com.example.notestaker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Upsert
    suspend fun updateOrInsert(note: Note) : Long

    @Query("DELETE FROM Note WHERE noteId IN (:noteIds)")
    suspend fun deleteNotes(noteIds: List<Long>)

    @Query("SELECT * FROM Note WHERE noteId= :noteId LIMIT 1")
    fun getNoteById(noteId: Long) : Flow<Note>

    @Query("SELECT * FROM Note")
    fun getAllNotes(): Flow<List<Note>>
}