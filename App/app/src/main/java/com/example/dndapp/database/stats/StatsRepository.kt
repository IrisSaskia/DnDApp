package com.example.dndapp.database.stats

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dndapp.database.DnDDatabase
import com.example.dndapp.model.stats.*

class StatsRepository(context: Context) {
    private val statsDAO: StatsDAO

    init {
        val database = DnDDatabase.getDatabase((context))
        statsDAO = database!!.statsDAO()
    }

    fun getStrength(currentDnDCharacter: MutableLiveData<Int>): LiveData<Strength?> {
        return statsDAO.getStrength(currentDnDCharacter)
    }

    fun getDexterity(currentDnDCharacter: MutableLiveData<Int>): LiveData<Dexterity?> {
        return statsDAO.getDexterity(currentDnDCharacter)
    }

    fun getConstitution(currentDnDCharacter: MutableLiveData<Int>): LiveData<Constitution?> {
        return statsDAO.getConstitution(currentDnDCharacter)
    }

    fun getIntelligence(currentDnDCharacter: MutableLiveData<Int>): LiveData<Intelligence?> {
        return statsDAO.getIntelligence(currentDnDCharacter)
    }

    fun getWisdom(currentDnDCharacter: MutableLiveData<Int>): LiveData<Wisdom?> {
        return statsDAO.getWisdom(currentDnDCharacter)
    }

    fun getCharisma(currentDnDCharacter: MutableLiveData<Int>): LiveData<Charisma?> {
        return statsDAO.getCharisma(currentDnDCharacter)
    }
}