package com.example.dndapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dndapp.database.characters.DnDCharacterRepository
import com.example.dndapp.model.DnDCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    var currentDnDCharacter: LiveData<DnDCharacter?> = dndCharacterRepository.getDnDCharacter(1)

    /*fun updateCharacter() {
        mainScope.launch {

        }
    }*/
}