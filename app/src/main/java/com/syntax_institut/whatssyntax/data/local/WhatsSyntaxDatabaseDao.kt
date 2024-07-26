package com.syntax_institut.whatssyntax.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.syntax_institut.whatssyntax.data.datamodel.Note
import retrofit2.http.DELETE

@Dao
interface WhatsSyntaxDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM notes_table WHERE id = :id")
    fun getNoteById(id: Long): LiveData<Note>

    @Query("DELETE FROM notes_table WHERE id = :id")
    suspend fun deleteNoteById(id: Long)

    @Query("SELECT COUNT(*) FROM notes_table")
    suspend fun getNumberOfNotes(): Int


}