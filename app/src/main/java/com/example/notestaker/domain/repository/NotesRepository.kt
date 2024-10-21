package com.example.notestaker.domain.repository

import com.example.notestaker.data.local.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun updateOrInsertNote(note: Note): Long
    suspend fun deleteNotes(notesIds: List<Long>)
    fun getNoteById(noteId: Long): Flow<Note>
    fun getAllNotes() : Flow<List<Note>>
}