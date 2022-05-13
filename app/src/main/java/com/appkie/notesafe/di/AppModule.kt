package com.appkie.notesafe.di

import android.app.Application
import androidx.room.Room
import com.appkie.notesafe.data.NoteDatabase
import com.appkie.notesafe.data.repository.CategoryRepository
import com.appkie.notesafe.data.repository.NoteRepository
import com.appkie.notesafe.data.repository.TodoRepository
import com.appkie.notesafe.util.Consts.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDatabase): NoteRepository {
        return NoteRepository(database.noteDao)
    }

    @Provides
    @Singleton
    fun provideTodoRepository(database: NoteDatabase): TodoRepository {
        return TodoRepository(database.todoDao)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(database: NoteDatabase): CategoryRepository {
        return CategoryRepository(database.categoryDao)
    }
}