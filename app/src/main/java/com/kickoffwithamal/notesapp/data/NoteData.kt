package com.kickoffwithamal.notesapp.data

import com.kickoffwithamal.notesapp.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Avatar", description = "A movie"),
            Note(title = "Dhoni", description = "A player"),
            Note(title = "Messi", description = "A goat"),
            Note(title = "MI", description = "A movie"),
            Note(title = "Pele", description = "A player"),
            Note(title = "Ronaldo", description = "A goat"),
            Note(title = "Empuran", description = "A movie"),
            Note(title = "Kohli", description = "A player"),
            Note(title = "Sachin", description = "A goat"),
            Note(title = "John Wick", description = "A movie"),
            Note(title = "Amal", description = "A player"),
            Note(title = "Neymar", description = "A goat")
        )
    }
}