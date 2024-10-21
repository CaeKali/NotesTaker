package com.example.notestaker.domain.useCases

import com.example.notestaker.domain.repository.NotesRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    operator fun invoke(noteId: Long) = notesRepository.getNoteById(noteId)
}