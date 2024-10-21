package com.example.notestaker.data.repository

import com.example.notestaker.data.local.Note
import com.example.notestaker.data.local.NoteDao
import com.example.notestaker.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NotesRepository {

    override suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    override suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    override suspend fun updateOrInsertNote(note: Note): Long  = noteDao.updateOrInsert(note)

    override suspend fun deleteNotes(notesIds: List<Long>) = noteDao.deleteNotes(notesIds)

    override fun getNoteById(noteId: Long): Flow<Note> = noteDao.getNoteById(noteId)

    override fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()

}