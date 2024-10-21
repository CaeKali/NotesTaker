package com.example.notestaker.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.notestaker.navigation.Notes
import com.example.notestaker.navigation.editorDestination
import com.example.notestaker.navigation.navigateToEditorScreen
import com.example.notestaker.navigation.notesDestination

@Composable
fun NotesTakerApp(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Notes){
        notesDestination(onAddNote = {
            navController.navigateToEditorScreen()
        },onNoteClick = { noteId ->
            navController.navigateToEditorScreen(noteId)
        })
        editorDestination(onBackClick = navController::popBackStack)
    }
}