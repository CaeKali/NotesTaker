package com.example.notestaker.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.notestaker.data.local.Note
import com.example.notestaker.ui.editor.EditorScreen
import com.example.notestaker.ui.notes.NotesScreen
import kotlinx.serialization.Serializable

@Serializable
object Notes

@Serializable
data class Editor(val noteId: Long? = null)

//fun NavController.navigateToNotesScreen(){
//    navigate( route = Notes)
//}

fun NavController.navigateToEditorScreen(noteId: Long? = null){
    navigate(route = Editor(noteId = noteId))
}

fun NavGraphBuilder.notesDestination(onAddNote:() -> Unit,onNoteClick: (Long) -> Unit) {
    composable<Notes> {
        NotesScreen(onAddNote = onAddNote,onNoteClick = onNoteClick)
    }
}

fun NavGraphBuilder.editorDestination(onBackClick:() -> Unit) {
    composable<Editor> { backStackTrace ->
        EditorScreen(onBackClick = onBackClick)
    }
}