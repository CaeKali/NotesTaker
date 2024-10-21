package com.example.notestaker.domain.useCases

import com.example.notestaker.data.local.Note
import com.example.notestaker.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(notesIds: List<Long>){
        notesRepository.deleteNotes(notesIds)
    }
}