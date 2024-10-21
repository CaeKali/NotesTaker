package com.example.notestaker.domain.useCases

import com.example.notestaker.data.local.Note
import com.example.notestaker.domain.repository.NotesRepository
import javax.inject.Inject

class UpdateOrInsertNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Note): Long = notesRepository.updateOrInsertNote(note)
}