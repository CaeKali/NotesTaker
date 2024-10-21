package com.example.notestaker.ui.editor

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.notestaker.data.local.Note
import com.example.notestaker.domain.useCases.GetNoteByIdUseCase
import com.example.notestaker.domain.useCases.UpdateOrInsertNoteUseCase
import com.example.notestaker.navigation.Editor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateOrInsertNoteUseCase: UpdateOrInsertNoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val TAG: String = "EditorViewModel"
    private var _uiState = MutableStateFlow(EditorUiState())
    val uiState: StateFlow<EditorUiState> = _uiState.asStateFlow()

    init {
        savedStateHandle.toRoute<Editor>().noteId?.let {
            Log.d(TAG, it.toString())
            loadNote(it)
        }
    }


    fun onTitleChange(title: String) {
        _uiState.update { currentState ->
            currentState.copy(noteTitle = title)
        }
    }

    fun onBodyChange(body: String) {
        _uiState.update { currentState ->
            currentState.copy(noteBody = body)
        }
    }

    private fun loadNote(noteId: Long){
        viewModelScope.launch {
            getNoteByIdUseCase(noteId).collect { note: Note ->
                _uiState.update { currentState ->
                    currentState.copy(
                        noteId = note.noteId,
                        noteTitle = note.noteTitle,
                        noteBody = note.noteBody
                    )
                }
            }
        }
    }

    fun onSaveClick() {
        if (_uiState.value.noteTitle != "" || _uiState.value.noteBody != "") {
            viewModelScope.launch {
              val newNotedId: Long = updateOrInsertNoteUseCase(
                    Note(
                        _uiState.value.noteId,
                        uiState.value.noteTitle,
                        _uiState.value.noteBody,
                        Date()
                    )
                )

                Log.d(TAG, "Saved Noted Id $newNotedId")
                _uiState.update { currentState ->
                    currentState.copy(noteId = newNotedId)
                }
            }
        }
    }
}