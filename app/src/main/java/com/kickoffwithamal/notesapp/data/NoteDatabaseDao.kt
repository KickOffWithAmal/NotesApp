package com.kickoffwithamal.notesapp.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.internal.Decoy
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kickoffwithamal.notesapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query(value = "SELECT * from notes_table")
    fun getNotes(): Flow<List<Note>> // Flow -> asynchronous data structure

    // suspend for using coroutines
    @Query(value = "SELECT * from notes_table where id =:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query(value = "DELETE from notes_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)
}
