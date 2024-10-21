package com.example.notestaker.ui.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notestaker.data.local.Note
import java.util.Date

@Composable
fun NotesList(
    notes: List<Note>,
    isSelectionMode: Boolean,
    selectedNotes: List<Long>,
    onNoteSelect: (Long) -> Unit,
    modifier: Modifier = Modifier,
    onNoteClick: (Long) -> Unit,
    onNoteLongClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(notes) { note ->
            val isNoteSelected = selectedNotes.contains(note.noteId)
            NotesItem(
                note,
                onNoteClick = onNoteClick,
                isSelectionMode = isSelectionMode,
                isNoteSelected = isNoteSelected,
                onNoteSelect = onNoteSelect,
                onNoteLongClick = onNoteLongClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListPreview() {
    val notes = listOf(
        Note(1, "Title", "Content", Date()),
        Note(1, "Title", "Content", Date()),
        Note(1, "Title", "Content", Date()),
        Note(1, "Title", "Content", Date()),
        Note(1, "Title", "Content", Date())
    )
//    NotesList(notes, modifier = Modifier.fillMaxSize())
}