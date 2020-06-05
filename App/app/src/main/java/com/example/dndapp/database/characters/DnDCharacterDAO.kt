package com.example.dndapp.database.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dndapp.model.DnDCharacter

@Dao
interface DnDCharacterDAO {
    @Insert
    suspend fun insertDnDCharacter(dndCharacter: DnDCharacter)

//    @Query("SELECT * FROM characterTable LIMIT 1")
    @Query("SELECT * FROM dndCharacterTable WHERE id = :currentDnDCharacter")
    fun getDnDCharacter(currentDnDCharacter: Int): LiveData<DnDCharacter?>

    @Update
    suspend fun updateDnDCharacter(dndCharacter: DnDCharacter)

    //UPDATE THE NOTES FOR THE SELECTED CHARACTER
    @Query("UPDATE dndCharacterTable SET note = :note WHERE id = :currentDnDCharacter")
    suspend fun updateNote(currentDnDCharacter: Int, note: String)

    //GET THE NOTES FROM THE SELECTED CHARACTER
    @Query("SELECT note FROM dndCharacterTable WHERE id = :currentDnDCharacter")
    fun getNote(currentDnDCharacter: Int): LiveData<String>

    //GET THE MOST RECENT LOADED CHARACTER FROM THE LAST TIME THE APP WAS ACTIVE
    @Query("SELECT id FROM dndCharacterTable WHERE active ORDER BY id LIMIT 1")
    fun getLoadedCharacter(): LiveData<Int>


//    //CURRENT CHARACTER
//    @Insert
//    suspend fun insertCurrentCharacter(currentAppState: CurrentAppState)
//
//    @Query("SELECT * FROM currentAppStateTable LIMIT 1")
//    fun getCurrentCharacter(): LiveData<CurrentAppState>
}