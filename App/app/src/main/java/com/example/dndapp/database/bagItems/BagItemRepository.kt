package com.example.dndapp.database.bagItems

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Transaction
import com.example.dndapp.database.DnDDatabase
import com.example.dndapp.model.BagItem
import com.example.dndapp.model.Money

class BagItemRepository(context: Context) {
    private val bagItemDAO: BagItemDAO

    init {
        val database = DnDDatabase.getDatabase((context))
        bagItemDAO = database!!.bagItemDAO()
    }

    suspend fun insertBagItem(bagItem: BagItem) {
        bagItemDAO.insertBagItem(bagItem)
    }

    suspend fun insertMoney(money: Money) {
        bagItemDAO.insertMoney(money)
    }

    @Transaction
    suspend fun updateMoney(platinum: Int, gold: Int, electrum: Int, silver: Int, copper: Int, currentDnDCharacter: Int) {
        bagItemDAO.updatePlatinum(platinum, currentDnDCharacter)
        bagItemDAO.updateGold(gold, currentDnDCharacter)
        bagItemDAO.updateElectrum(electrum, currentDnDCharacter)
        bagItemDAO.updateSilver(silver, currentDnDCharacter)
        bagItemDAO.updateCopper(copper, currentDnDCharacter)
    }

    fun getMoney(currentDnDCharacter: Int): LiveData<Money> {
        return bagItemDAO.getMoney(currentDnDCharacter)
    }

    fun getAllBagItems(currentDnDCharacter: Int): LiveData<List<BagItem>> {
        return bagItemDAO.getAllBagItems(currentDnDCharacter)
    }
}