package com.example.dndapp.ui.extra.notes.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.dndapp.database.notes.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val notesRepository =
        NotesRepository(application.applicationContext)

    val notes = notesRepository.getNotes()
}