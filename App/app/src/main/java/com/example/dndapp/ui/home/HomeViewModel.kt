package com.example.dndapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dndapp.database.characters.DnDCharacterRepository
import com.example.dndapp.database.stats.StatsRepository
import com.example.dndapp.model.DnDCharacter
import com.example.dndapp.model.stats.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)
    private val statsRepository = StatsRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    var currentDnDCharacter: LiveData<DnDCharacter?> = dndCharacterRepository.getDnDCharacter(1)
    var currentStrength: LiveData<Strength?> = statsRepository.getStrength(1)
    var currentDexterity: LiveData<Dexterity?> = statsRepository.getDexterity(1)
    var currentConstitution: LiveData<Constitution?> = statsRepository.getConstitution(1)
    var currentIntelligence: LiveData<Intelligence?> = statsRepository.getIntelligence(1)
    var currentWisdom: LiveData<Wisdom?> = statsRepository.getWisdom(1)
    var currentCharisma: LiveData<Charisma?> = statsRepository.getCharisma(1)

    //TODO: iets waarmee alles in 1 zit???
    //TODO: moet er 1 viewmodel zijn waarin dit maar 1x gedaan hoeft te worden?

    /*fun updateCharacter() {
        mainScope.launch {

        }
    }*/
}