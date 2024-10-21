package com.example.notestaker.ui.notes.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notestaker.data.local.Note
import java.util.Date

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesItem(
    note: Note,
    modifier: Modifier = Modifier,
    isNoteSelected: Boolean,
    isSelectionMode: Boolean,
    onNoteSelect: (Long) -> Unit,
    onNoteClick: (Long) -> Unit,
    onNoteLongClick: (Long) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(onClick = {
                if (isSelectionMode) {
                    onNoteSelect(note.noteId)
                } else {
                    onNoteClick(note.noteId)
                }
            }, onLongClick = {
                onNoteLongClick(note.noteId)
            }),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    note.noteTitle,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    note.noteBody,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            if (isSelectionMode) {
                Checkbox(checked = isNoteSelected, onCheckedChange = {
                    onNoteSelect(note.noteId)
                })
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun NotesItemPreview() {
    NotesItem(note = Note(1, "Title", "Content", Date()),
        isNoteSelected = true,
        isSelectionMode = true,
        onNoteClick = {

        },
        onNoteLongClick = {

        },
        onNoteSelect = {

        })
}