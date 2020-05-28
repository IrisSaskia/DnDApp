package com.example.dndapp.ui.extra.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dndapp.database.notes.NotesRepository
import com.example.dndapp.model.notes.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val notesRepository = NotesRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val notesBeforeSending = notesRepository.getNotes()

    val notes = MutableLiveData<Notes?>()

//TODO: misschien nog error handling voor bijvoorbeeld het opslaan van een lege notitie?

    fun updateNotes() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                notesRepository.updateNotes(notes.value!!)
            }
        }
    }
}