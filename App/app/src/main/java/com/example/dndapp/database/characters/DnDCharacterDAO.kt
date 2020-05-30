package com.example.dndapp.database.characters

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dndapp.model.characters.DnDCharacter

@Dao
interface DnDCharacterDAO {
    @Insert
    suspend fun insertDnDCharacter(dndCharacter: DnDCharacter)

//    @Query("SELECT * FROM characterTable LIMIT 1")
    @Query("SELECT * FROM dndCharacterTable")
    fun getDnDCharacter(): LiveData<DnDCharacter?>

    @Update
    suspend fun updateDnDCharacter(dndCharacter: DnDCharacter)

    @Query("UPDATE dndCharacterTable SET note = :note WHERE id = :currentDnDCharacter")
    suspend fun updateNote(currentDnDCharacter: Int, note: String)

    @Query("SELECT note FROM dndCharacterTable WHERE id = :currentDnDCharacter")
    fun getNote(currentDnDCharacter: Int): LiveData<String>
}