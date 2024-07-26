package com.syntax_institut.whatssyntax.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.syntax_institut.whatssyntax.data.datamodel.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: WhatsSyntaxDatabaseDao
}

private lateinit var INSTANCE: NoteDatabase

fun getDatabase(context: Context): NoteDatabase {
    synchronized(NoteDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            NoteDatabase::class.java,
            "notes_database")
            .build()
    }
        return INSTANCE
    }
}

