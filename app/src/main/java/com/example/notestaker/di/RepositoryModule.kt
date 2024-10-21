package com.example.notestaker.di

import com.example.notestaker.data.repository.NotesRepositoryImpl
import com.example.notestaker.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    abstract fun bindNotesRepository(notesRepositoryImpl: NotesRepositoryImpl) : NotesRepository
}