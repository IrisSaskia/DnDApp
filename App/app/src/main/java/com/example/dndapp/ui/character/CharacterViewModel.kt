package com.example.dndapp.ui.character

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dndapp.database.characters.DnDCharacterRepository
import com.example.dndapp.model.DnDCharacter

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)

    var currentDnDCharacter: LiveData<DnDCharacter?> = dndCharacterRepository.getDnDCharacter(1)
}