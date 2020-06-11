package com.example.dndapp.database.stats

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Transaction
import com.example.dndapp.database.DnDDatabase
import com.example.dndapp.model.stats.*

class StatsRepository(context: Context) {
    private val statsDAO: StatsDAO

    init {
        val database = DnDDatabase.getDatabase((context))
        statsDAO = database!!.statsDAO()
    }

    //Delete all stats at the same time
    @Transaction
    fun deleteStats(dndCharacterID: Int) {
        statsDAO.deleteStrength(dndCharacterID)
        statsDAO.deleteDexterity(dndCharacterID)
        statsDAO.deleteConstitution(dndCharacterID)
        statsDAO.deleteWisdom(dndCharacterID)
        statsDAO.deleteIntelligence(dndCharacterID)
        statsDAO.deleteCharisma(dndCharacterID)
    }

    //Insert functions for all stats
    suspend fun insertStrength(strength: Strength) {
        statsDAO.insertStrength(strength)
    }
    suspend fun insertDexterity(dexterity: Dexterity) {
        statsDAO.insertDexterity(dexterity)
    }
    suspend fun insertIntelligence(intelligence: Intelligence) {
        statsDAO.insertIntelligence(intelligence)
    }
    suspend fun insertWisdom(wisdom: Wisdom) {
        statsDAO.insertWisdom(wisdom)
    }
    suspend fun insertCharisma(charisma: Charisma) {
        statsDAO.insertCharisma(charisma)
    }
    suspend fun insertConstitution(constitution: Constitution) {
        statsDAO.insertConstitution(constitution)
    }

    //Get functions for all stats
    fun getStrength(currentDnDCharacter: Int): LiveData<Strength?> {
        return statsDAO.getStrength(currentDnDCharacter)
    }
    fun getDexterity(currentDnDCharacter: Int): LiveData<Dexterity?> {
        return statsDAO.getDexterity(currentDnDCharacter)
    }
    fun getConstitution(currentDnDCharacter: Int): LiveData<Constitution?> {
        return statsDAO.getConstitution(currentDnDCharacter)
    }
    fun getIntelligence(currentDnDCharacter: Int): LiveData<Intelligence?> {
        return statsDAO.getIntelligence(currentDnDCharacter)
    }
    fun getWisdom(currentDnDCharacter: Int): LiveData<Wisdom?> {
        return statsDAO.getWisdom(currentDnDCharacter)
    }
    fun getCharisma(currentDnDCharacter: Int): LiveData<Charisma?> {
        return statsDAO.getCharisma(currentDnDCharacter)
    }
}