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

    //Insert a character into the database
    suspend fun insertDnDCharacter(dndCharacter: DnDCharacter) {
        dndCharacterDAO.insertDnDCharacter(dndCharacter)
    }

    //Deletion of the character
    suspend fun deleteDnDCharacter(dndCharacter: DnDCharacter) {
        dndCharacterDAO.deleteDnDCharacter(dndCharacter)
    }

    //Get a character from the database based on id
    fun getDnDCharacter(currentDnDCharacter: Int): LiveData<DnDCharacter?> {
        return dndCharacterDAO.getDnDCharacter(currentDnDCharacter)
    }

    //Get all characters from the database
    fun getAllCharacters(): LiveData<List<DnDCharacter>> {
        return dndCharacterDAO.getAllCharacters()
    }

    //Get the characterID of the character that is currently active
    fun getLoadedCharacter(): LiveData<Int> {
        return  dndCharacterDAO.getLoadedCharacter()
    }

    //Set the old current character to inactive while setting the new character to active
    @Transaction
    suspend fun changeCurrentCharacter(chosenCharacterID: Int, currentCharacterID: Int) {
        dndCharacterDAO.makeActive(chosenCharacterID)
        dndCharacterDAO.makeInactive(currentCharacterID)
    }

    //update the note for the current character
    suspend fun updateNote(currentDnDCharacter: Int, note: String) {
        dndCharacterDAO.updateNote(currentDnDCharacter, note)
    }

    //Load the note for the current character
    fun getNote(currentDnDCharacter: Int): LiveData<String> {
        return dndCharacterDAO.getNote(currentDnDCharacter)
    }
}