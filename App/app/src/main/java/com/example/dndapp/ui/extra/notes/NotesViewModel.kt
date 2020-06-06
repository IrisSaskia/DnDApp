package com.example.dndapp.ui.extra.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dndapp.database.characters.DnDCharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

//    val notes = dndCharacterRepository.getDnDCharacter()
    var note: LiveData<String> = dndCharacterRepository.getNote(1)
//TODO: was mutablelivedata nou nodig???
//TODO: misschien nog error handling voor bijvoorbeeld het opslaan van een lege notitie?
    //TODO: dit nog naar mainviewmodel verplaatsen

    fun updateNotes() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                dndCharacterRepository.updateNote(1, note.value!!)
            }
        }
    }
}