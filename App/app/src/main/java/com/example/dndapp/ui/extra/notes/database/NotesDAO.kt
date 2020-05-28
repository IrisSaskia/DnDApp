package com.example.dndapp.ui.extra.notes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dndapp.ui.extra.notes.model.Notes

@Dao
interface NotesDAO {
    @Insert
    suspend fun insertNotes(notes: Notes)

    @Query("SELECT * FROM notesTable LIMIT 1")
    fun getNotes(): LiveData<Notes?>

    @Update
    suspend fun updateNotes(notes: Notes)
}