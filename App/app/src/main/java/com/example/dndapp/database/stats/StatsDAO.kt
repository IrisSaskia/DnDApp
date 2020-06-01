package com.example.dndapp.database.stats

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dndapp.model.DnDCharacter
import com.example.dndapp.model.stats.*

@Dao
interface StatsDAO {
    @Insert
    suspend fun insertStrength(strength: Strength)

    @Insert
    suspend fun insertDexterity(dexterity: Dexterity)

    @Insert
    suspend fun insertConstitution(constitution: Constitution)

    @Insert
    suspend fun insertIntelligence(intelligence: Intelligence)

    @Insert
    suspend fun insertWisdom(wisdom: Wisdom)

    @Insert
    suspend fun insertCharisma(charisma: Charisma)

    @Query("SELECT * FROM strengthTable WHERE characterID = :currentDnDCharacter")
    fun getStrength(currentDnDCharacter: Int): LiveData<Strength?>

    @Query("SELECT * FROM dexterityTable WHERE characterID = :currentDnDCharacter")
    fun getDexterity(currentDnDCharacter: Int): LiveData<Dexterity?>

    @Query("SELECT * FROM constitutionTable WHERE characterID = :currentDnDCharacter")
    fun getConstitution(currentDnDCharacter: Int): LiveData<Constitution?>

    @Query("SELECT * FROM intelligenceTable WHERE characterID = :currentDnDCharacter")
    fun getIntelligence(currentDnDCharacter: Int): LiveData<Intelligence?>

    @Query("SELECT * FROM wisdomTable WHERE characterID = :currentDnDCharacter")
    fun getWisdom(currentDnDCharacter: Int): LiveData<Wisdom?>

    @Query("SELECT * FROM charismaTable WHERE characterID = :currentDnDCharacter")
    fun getCharisma(currentDnDCharacter: Int): LiveData<Charisma?>

    /*@Query("UPDATE strengthTable AS str INNER JOIN dndCharacterTable as dnd ON (str.id=dnd.strengthID) WHERE dnd.id = :currentDnDCharacter")
    suspend fun updateStrength(currentDnDCharacter: Int, strength: Strength)*/
}