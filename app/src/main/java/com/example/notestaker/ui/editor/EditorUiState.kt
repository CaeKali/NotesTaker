package com.example.notestaker.ui.editor

data class EditorUiState(
    val noteId: Long = 0,
    val noteTitle: String = "",
    val noteBody: String = ""
)
