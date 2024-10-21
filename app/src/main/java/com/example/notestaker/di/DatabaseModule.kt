package com.example.notestaker.di

import android.content.Context
import androidx.room.Room
import com.example.notestaker.data.local.NoteDao
import com.example.notestaker.data.local.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(context, NotesDatabase::class.java, "notes.db").build()
    }

    @Provides
    fun providesNoteDao(notesDatabase: NotesDatabase) : NoteDao{
        return notesDatabase.noteDao()
    }
}