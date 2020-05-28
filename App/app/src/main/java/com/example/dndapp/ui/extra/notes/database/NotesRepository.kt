package com.example.dndapp.ui.extra.notes.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.dndapp.ui.extra.notes.model.Notes

class NotesRepository(context: Context) {

    private val notesDAO: NotesDAO

    init {
        val database = NotesRoomDatabase.getDatabase(context)
        notesDAO = database!!.notesDAO()
    }

    fun getNotes(): LiveData<Notes?> {
        return notesDAO.getNotes()
    }

    suspend fun updateNotes(notes: Notes) {
        notesDAO.updateNotes(notes)
    }
}