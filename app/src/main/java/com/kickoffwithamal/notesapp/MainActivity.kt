package com.kickoffwithamal.notesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.kickoffwithamal.notesapp.screen.NoteScreen
import com.kickoffwithamal.notesapp.screen.NoteViewModel
import com.kickoffwithamal.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val noteViewmodel: NoteViewModel by viewModels()
                    NotesApp(noteViewmodel)
                }
            }
        }
    }

    @Composable
    fun NotesApp(notesViewModel: NoteViewModel) {
        var noteList = notesViewModel.noteList.collectAsState().value
        val coroutineScope = rememberCoroutineScope()

        NoteScreen(
            notes = noteList, onAddNote = {
                notesViewModel.addNote(it)
            },
            onRemoveNote = {
                coroutineScope.launch(Dispatchers.IO) {
                    notesViewModel.removeNote(it)
                }
            })
    }
}