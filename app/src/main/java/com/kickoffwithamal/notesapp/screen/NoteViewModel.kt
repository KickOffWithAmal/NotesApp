package com.kickoffwithamal.notesapp.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kickoffwithamal.notesapp.model.Note
import com.kickoffwithamal.notesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()
    // var noteList = mutableStateListOf<Note>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect { listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()) {
                        Log.d("Empty", "Empty List")
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
        // noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note = note)
        refreshNoteList()
    }
    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }
    fun removeNote(note: Note) =
        viewModelScope.launch {
            repository.deleteNotes(note = note)
            refreshNoteList()
        }

    fun removeAllNotes() = viewModelScope.launch { repository.deleteNotes() }

    fun getAllNotes(): Flow<List<Note>> = repository.getAllNotes()

    // make sure DB and UI gets updated w.r.t to changes made
    private suspend fun refreshNoteList() {
        repository.getAllNotes().collect { updatedNotes ->
            _noteList.value = updatedNotes
        }
    }

}