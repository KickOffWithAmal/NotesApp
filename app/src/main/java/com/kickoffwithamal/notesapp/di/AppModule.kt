package com.kickoffwithamal.notesapp.di

import android.content.Context
import androidx.room.Room
import com.kickoffwithamal.notesapp.data.NoteDatabase
import com.kickoffwithamal.notesapp.data.NoteDatabaseDao
import com.kickoffwithamal.notesapp.network.ApiInterface
import com.kickoffwithamal.notesapp.network.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideApiInterface(): ApiInterface {
        return NetworkManager.apiInterface
    }
}