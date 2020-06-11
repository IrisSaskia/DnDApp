package com.example.dndapp.database.bagItems

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dndapp.model.BagItem
import com.example.dndapp.model.Money

@Dao
interface BagItemDAO {
    //Inserts for money and items
    @Insert
    suspend fun insertBagItem(bagItem: BagItem)

    @Insert
    suspend fun insertMoney(money: Money)

    //UPDATE THE money FOR THE SELECTED CHARACTER
    @Query("UPDATE moneyTable SET amountOfPlatinum = :platinum WHERE characterID = :currentDnDCharacter")
    suspend fun updatePlatinum(platinum: Int, currentDnDCharacter: Int)

    @Query("UPDATE moneyTable SET amountOfGold = :gold WHERE characterID = :currentDnDCharacter")
    suspend fun updateGold(gold: Int, currentDnDCharacter: Int)

    @Query("UPDATE moneyTable SET amountOfElectrum = :electrum WHERE characterID = :currentDnDCharacter")
    suspend fun updateElectrum(electrum: Int, currentDnDCharacter: Int)

    @Query("UPDATE moneyTable SET amountOfSilver = :silver WHERE characterID = :currentDnDCharacter")
    suspend fun updateSilver(silver: Int, currentDnDCharacter: Int)

    @Query("UPDATE moneyTable SET amountOfCopper = :copper WHERE characterID = :currentDnDCharacter")
    suspend fun updateCopper(copper: Int, currentDnDCharacter: Int)

    //GET THE money FROM THE SELECTED CHARACTER
    @Query("SELECT * FROM moneyTable WHERE characterID = :currentDnDCharacter")
    fun getMoney(currentDnDCharacter: Int): LiveData<Money>

    @Query("SELECT * FROM bagItemTable WHERE characterID = :currentDnDCharacter")
    fun getAllBagItems(currentDnDCharacter: Int): LiveData<List<BagItem>>
}