package com.example.dndapp.database.characters

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Transaction
import com.example.dndapp.database.DnDDatabase
import com.example.dndapp.model.DnDCharacter

class DnDCharacterRepository(context: Context) {
    private val dndCharacterDAO: DnDCharacterDAO

    init {
        val database = DnDDatabase.getDatabase((context))
        dndCharacterDAO = database!!.dndCharacterDAO()
    }

    suspend fun insertDnDCharacter(dndCharacter: DnDCharacter) {
        dndCharacterDAO.insertDnDCharacter(dndCharacter)
    }

    fun getDnDCharacter(currentDnDCharacter: Int): LiveData<DnDCharacter?> {
        return dndCharacterDAO.getDnDCharacter(currentDnDCharacter)
    }

    fun getAllCharacters(): LiveData<List<DnDCharacter>> {
        return dndCharacterDAO.getAllCharacters()
    }

    @Transaction
    suspend fun changeCurrentCharacter(chosenCharacterID: Int, currentCharacterID: Int) {
        dndCharacterDAO.makeActive(chosenCharacterID)
        dndCharacterDAO.makeInactive(currentCharacterID)
    }

    /*suspend fun updateDnDCharacter(dndCharacter: DnDCharacter) {
        dndCharacterDAO.updateDnDCharacter(dndCharacter)
    }*/

    suspend fun updateNote(currentDnDCharacter: Int, note: String) {
        dndCharacterDAO.updateNote(currentDnDCharacter, note)
    }

    fun getNote(currentDnDCharacter: Int): LiveData<String> {
        return dndCharacterDAO.getNote(currentDnDCharacter)
    }

    fun getLoadedCharacter(): LiveData<Int> {
        return  dndCharacterDAO.getLoadedCharacter()
    }

//    //CURRENT CHARACTER
//    fun getCurrentCharacter(): LiveData<CurrentAppState> {
//        return dndCharacterDAO.getCurrentCharacter()
//    }
}