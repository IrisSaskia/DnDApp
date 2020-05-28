package com.example.dndapp.ui.extra.notes.ui

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
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()

//TODO: misschien nog error handling voor bijvoorbeeld het opslaan van een lege notitie?

    fun updateNotes() {
        if (areNotesValid()) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    notesRepository.updateNotes(notes.value!!)
                }
                success.value = true
            }
        }
    }

    private fun areNotesValid(): Boolean {
        return when (notes.value) {
            null -> {
                error.value = "Note must not be null"
                false
            }
            else -> true
        }
    }
}