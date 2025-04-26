package com.kickoffwithamal.notesapp.repository

import com.kickoffwithamal.notesapp.data.NoteDatabaseDao
import com.kickoffwithamal.notesapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    suspend fun addNote(note: Note) = noteDatabaseDao.insertNote(note = note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note = note)
    suspend fun deleteNotes(note: Note) = noteDatabaseDao.deleteNote(note = note)
    suspend fun deleteNotes() = noteDatabaseDao.deleteAll()
    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()

}