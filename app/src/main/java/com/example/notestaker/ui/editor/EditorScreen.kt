package com.example.notestaker.ui.editor

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notestaker.ui.editor.components.EditorField
import com.example.notestaker.ui.editor.components.EditorTopBar

@Composable
fun EditorScreen(editorViewModel: EditorViewModel = hiltViewModel(), onBackClick: () -> Unit) {
    val uiState by editorViewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(topBar = {
        EditorTopBar("Editor", modifier = Modifier.background(color = Color.White), onSaveClick = {
            editorViewModel.onSaveClick()
            Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT).show()
        }, onBackClick = onBackClick)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            EditorField(value = uiState.noteTitle, placeHolderText = "Title", onValueChange = {
                editorViewModel.onTitleChange(it)
            }, textStyle = MaterialTheme.typography.titleLarge)
            HorizontalDivider()
            EditorField(value = uiState.noteBody, placeHolderText = "Write Here", onValueChange = {
                editorViewModel.onBodyChange(it)

            }, modifier = Modifier.weight(1f))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditorScreenPreview() {
    EditorScreen(onBackClick = {

    })
}