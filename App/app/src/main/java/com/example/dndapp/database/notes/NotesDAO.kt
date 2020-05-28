package com.example.dndapp.database.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dndapp.model.notes.Notes

@Dao
interface NotesDAO {
    @Insert
    suspend fun insertNotes(notes: Notes)

    @Query("SELECT * FROM notesTable LIMIT 1")
    fun getNotes(): LiveData<Notes?>

    @Update
    suspend fun updateNotes(notes: Notes)
}