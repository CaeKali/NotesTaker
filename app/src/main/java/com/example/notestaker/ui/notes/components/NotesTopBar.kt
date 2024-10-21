package com.example.notestaker.ui.notes.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesTopBar(title: String,isSelectionMode: Boolean,onDeleteNotes:() -> Unit,onCloseSelectionMode:() -> Unit) {
    TopAppBar(title = {
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge
        )
    }, actions = {
        if (isSelectionMode){
            IconButton(onClick = onDeleteNotes) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete Icon")
            }

            IconButton(onClick = onCloseSelectionMode) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "Close Selection Mode Icon")
            }
        }
    })
}
