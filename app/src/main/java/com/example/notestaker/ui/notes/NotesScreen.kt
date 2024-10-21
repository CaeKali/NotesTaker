package com.example.notestaker.ui.notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notestaker.data.local.Note
import com.example.notestaker.ui.notes.components.NotesList
import com.example.notestaker.ui.notes.components.NotesTopBar
import java.util.Date

@Composable
fun NotesScreen(
    notesViewModel: NotesViewModel = hiltViewModel(),
    onAddNote: () -> Unit,
    onNoteClick: (Long) -> Unit
) {
    val uiState by notesViewModel.uiState.collectAsState()
    val selectedNotes by notesViewModel.selectedItems.collectAsState()

    Scaffold(topBar = {
        NotesTopBar(
            "Notes",
            uiState.isSelectionMode,
            onDeleteNotes = notesViewModel::onDeleteNotesEvent,
            onCloseSelectionMode = notesViewModel::onCloseSelectionModeEvent
        )
    }, floatingActionButton = {
        if (!uiState.isSelectionMode){
            FloatingActionButton(onClick = onAddNote) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    }, modifier = Modifier.fillMaxSize()) { innerPadding ->
        NotesList(
            notes = uiState.notes,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onNoteClick = onNoteClick,
            selectedNotes = selectedNotes,
            isSelectionMode = uiState.isSelectionMode,
            onNoteSelect = notesViewModel::onSelectNote,
            onNoteLongClick = {
                notesViewModel.onNoteLongClick()
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    val notes = listOf(
        Note(1, "Title", "Content", Date()),
        Note(1, "Title", "Content", Date()),
        Note(1, "Title", "Content", Date()),
        Note(1, "Title", "Content", Date()),
        Note(1, "Title", "Content", Date())
    )
//    NotesScreen(NotesUiState(notes = notes))
}