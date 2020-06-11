package com.example.dndapp.database.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.dndapp.model.DnDCharacter

@Dao
interface DnDCharacterDAO {
    //Insert function for the DnD Character
    @Insert
    suspend fun insertDnDCharacter(dndCharacter: DnDCharacter)

    //Get all data from the current DnD Character
    @Query("SELECT * FROM dndCharacterTable WHERE id = :currentDnDCharacter")
    fun getDnDCharacter(currentDnDCharacter: Int): LiveData<DnDCharacter?>

    //Delete a character from the database
    @Delete
    suspend fun deleteDnDCharacter(dndCharacter: DnDCharacter)

    //Get all characters that are in the database
    @Query("SELECT * FROM dndCharacterTable")
    fun getAllCharacters(): LiveData<List<DnDCharacter>>


    //GET THE MOST RECENT LOADED CHARACTER FROM THE LAST TIME THE APP WAS ACTIVE
    @Query("SELECT id FROM dndCharacterTable WHERE active ORDER BY id LIMIT 1")
    fun getLoadedCharacter(): LiveData<Int>

    @Query("UPDATE dndCharacterTable SET active = 1 WHERE id = :chosenCharacterID")
    fun makeActive(chosenCharacterID: Int)

    @Query("UPDATE dndCharacterTable SET active = 0 WHERE id = :currentCharacterID")
    fun makeInactive(currentCharacterID: Int)

    //////////////////////////////
    //          NOTES           //
    //////////////////////////////

    //UPDATE THE NOTES FOR THE SELECTED CHARACTER
    @Query("UPDATE dndCharacterTable SET note = :note WHERE id = :currentDnDCharacter")
    suspend fun updateNote(currentDnDCharacter: Int, note: String)

    //GET THE NOTES FROM THE SELECTED CHARACTER
    @Query("SELECT note FROM dndCharacterTable WHERE id = :currentDnDCharacter")
    fun getNote(currentDnDCharacter: Int): LiveData<String>
}