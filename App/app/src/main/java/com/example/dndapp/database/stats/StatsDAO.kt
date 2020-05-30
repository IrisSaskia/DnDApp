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

    @Query("SELECT str.* FROM strengthTable AS str INNER JOIN dndCharacterTable as dnd ON (str.id=dnd.strengthID) WHERE dnd.id = :currentDnDCharacter")
    fun getStrength(currentDnDCharacter: Int): LiveData<Strength?>

    @Query("SELECT dex.* FROM dexterityTable AS dex INNER JOIN dndCharacterTable as dnd ON (dex.id=dnd.strengthID) WHERE dnd.id = :currentDnDCharacter")
    fun getDexterity(currentDnDCharacter: Int): LiveData<Dexterity?>

    @Query("SELECT con.* FROM constitutionTable AS con INNER JOIN dndCharacterTable as dnd ON (con.id=dnd.strengthID) WHERE dnd.id = :currentDnDCharacter")
    fun getConstitution(currentDnDCharacter: Int): LiveData<Constitution?>

    @Query("SELECT int.* FROM intelligenceTable AS int INNER JOIN dndCharacterTable as dnd ON (int.id=dnd.strengthID) WHERE dnd.id = :currentDnDCharacter")
    fun getIntelligence(currentDnDCharacter: Int): LiveData<Intelligence?>

    @Query("SELECT wis.* FROM wisdomTable AS wis INNER JOIN dndCharacterTable as dnd ON (wis.id=dnd.strengthID) WHERE dnd.id = :currentDnDCharacter")
    fun getWisdom(currentDnDCharacter: Int): LiveData<Wisdom?>

    @Query("SELECT cha.* FROM charismaTable AS cha INNER JOIN dndCharacterTable as dnd ON (cha.id=dnd.strengthID) WHERE dnd.id = :currentDnDCharacter")
    fun getCharisma(currentDnDCharacter: Int): LiveData<Charisma?>

    /*@Query("UPDATE strengthTable AS str INNER JOIN dndCharacterTable as dnd ON (str.id=dnd.strengthID) WHERE dnd.id = :currentDnDCharacter")
    suspend fun updateStrength(currentDnDCharacter: Int, strength: Strength)*/
}