package com.example.dndapp.database.bagItems

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dndapp.model.BagItem

@Dao
interface BagItemDAO {
    @Insert
    suspend fun insertBagItem(bagItem: BagItem)

    @Query("SELECT * FROM bagItemTable WHERE characterID = :currentDnDCharacter")
    fun getAllBagItems(currentDnDCharacter: Int): LiveData<List<BagItem>>
}