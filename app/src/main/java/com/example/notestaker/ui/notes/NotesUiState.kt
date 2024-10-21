package com.example.notestaker.ui.notes

import com.example.notestaker.data.local.Note

data class NotesUiState(
    val notes: List<Note> = emptyList<Note>(),
    val isSelectionMode: Boolean = false
)

