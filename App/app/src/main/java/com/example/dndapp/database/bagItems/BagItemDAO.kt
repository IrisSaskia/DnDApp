package com.example.dndapp.database.bagItems

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dndapp.model.BagItem
import com.example.dndapp.model.Money

@Dao
interface BagItemDAO {
    @Insert
    suspend fun insertBagItem(bagItem: BagItem)

    @Insert
    suspend fun insertMoney(money: Money)

    //UPDATE THE money FOR THE SELECTED CHARACTER
    @Query("UPDATE moneyTable SET amountOfPlatinum = :platinum AND" +
            "SET amountOfGold = :gold AND" +
            "SET amountOfElectrum = :electrum AND" +
            "SET amountOfSilver = :silver AND" +
            "SET amountOfCopper = :copper WHERE id = :currentDnDCharacter")
    suspend fun updateMoney(platinum: Int, gold: Int, electrum: Int, silver: Int, copper: Int)

    //GET THE money FROM THE SELECTED CHARACTER
    @Query("SELECT * FROM moneyTable WHERE characterID = :currentDnDCharacter")
    fun getMoney(currentDnDCharacter: Int): LiveData<Money>

    @Query("SELECT * FROM bagItemTable WHERE characterID = :currentDnDCharacter")
    fun getAllBagItems(currentDnDCharacter: Int): LiveData<List<BagItem>>
}