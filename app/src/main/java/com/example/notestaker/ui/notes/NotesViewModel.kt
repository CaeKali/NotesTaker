package com.example.notestaker.ui.notes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notestaker.data.local.Note
import com.example.notestaker.domain.useCases.DeleteNotesUseCase
import com.example.notestaker.domain.useCases.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val getNotesUseCase: GetNotesUseCase,private val deleteNotesUseCase: DeleteNotesUseCase) :
    ViewModel() {
        private val TAG:String = "NotesViewModel"
    private var _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> = _uiState.asStateFlow()
    private var _selectedItems = MutableStateFlow<List<Long>>(emptyList())
    val selectedItems: StateFlow<List<Long>> = _selectedItems.asStateFlow()

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            getNotesUseCase().collect { notes: List<Note> ->
                Log.d(TAG,"Clicked Note Id: $notes.toString()")
                _uiState.update { currentState ->
                    currentState.copy(notes = notes)
                }
            }
        }
    }

    fun onDeleteNotesEvent(){
        viewModelScope.launch{
            deleteNotesUseCase(_selectedItems.value)
        }
        onCloseSelectionModeEvent()
    }

    fun onCloseSelectionModeEvent(){
        _uiState.update { currentState ->
            currentState.copy(isSelectionMode = false)
        }
        _selectedItems.value = emptyList<Long>()
    }

    fun onNoteLongClick(){
        _uiState.update { currentState ->
            currentState.copy(isSelectionMode = true)
        }
    }

    fun onSelectNote(noteId:Long){
        val updatedList = if (_selectedItems.value.contains(noteId)){
            _selectedItems.value - noteId
        }else{
            _selectedItems.value + noteId
        }
        _selectedItems.value = updatedList

        Log.d(TAG,"Selected Note Ids ${_selectedItems.value}")
    }
}