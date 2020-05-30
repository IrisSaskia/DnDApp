package com.example.dndapp.database.characters

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.dndapp.database.DnDDatabase
import com.example.dndapp.model.DnDCharacter

class DnDCharacterRepository(context: Context) {
    private val dndCharacterDAO: DnDCharacterDAO

    init {
        val database =
            DnDDatabase.getDatabase((
                    context
                    ))
        dndCharacterDAO = database!!.dndCharacterDAO()
    }

    fun getDnDCharacter(currentDnDCharacter: Int): LiveData<DnDCharacter?> {
        return dndCharacterDAO.getDnDCharacter(currentDnDCharacter)
    }

    suspend fun updateDnDCharacter(dndCharacter: DnDCharacter) {
        dndCharacterDAO.updateDnDCharacter(dndCharacter)
    }

    suspend fun updateNote(currentDnDCharacter: Int, note: String) {
        dndCharacterDAO.updateNote(currentDnDCharacter, note)
    }

    fun getNote(currentDnDCharacter: Int): LiveData<String> {
        return dndCharacterDAO.getNote(currentDnDCharacter)
    }
}