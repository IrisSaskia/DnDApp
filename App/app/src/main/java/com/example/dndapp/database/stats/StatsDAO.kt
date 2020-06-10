package com.example.dndapp.database.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dndapp.model.stats.*

@Dao
interface StatsDAO {
    //All insert functions for the six stats
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


    //All delete functions for the six stats
    @Query("DELETE FROM strengthTable WHERE characterID = :dndCharacterID")
    fun deleteStrength(dndCharacterID: Int)

    @Query("DELETE FROM dexterityTable WHERE characterID = :dndCharacterID")
    fun deleteDexterity(dndCharacterID: Int)

    @Query("DELETE FROM constitutionTable WHERE characterID = :dndCharacterID")
    fun deleteConstitution(dndCharacterID: Int)

    @Query("DELETE FROM intelligenceTable WHERE characterID = :dndCharacterID")
    fun deleteIntelligence(dndCharacterID: Int)

    @Query("DELETE FROM wisdomTable WHERE characterID = :dndCharacterID")
    fun deleteWisdom(dndCharacterID: Int)

    @Query("DELETE FROM charismaTable WHERE characterID = :dndCharacterID")
    fun deleteCharisma(dndCharacterID: Int)


    //All get functions for the six stats
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
}