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

    //Updates all money values for the current character at the same time
    @Transaction
    suspend fun updateMoney(currentDnDCharacter: Int, money: Money) {
        bagItemDAO.updatePlatinum(money.amountOfPlatinum, currentDnDCharacter)
        bagItemDAO.updateGold(money.amountOfGold, currentDnDCharacter)
        bagItemDAO.updateElectrum(money.amountOfElectrum, currentDnDCharacter)
        bagItemDAO.updateSilver(money.amountOfSilver, currentDnDCharacter)
        bagItemDAO.updateCopper(money.amountOfCopper, currentDnDCharacter)
    }

    fun getMoney(currentDnDCharacter: Int): LiveData<Money> {
        return bagItemDAO.getMoney(currentDnDCharacter)
    }

    fun getAllBagItems(currentDnDCharacter: Int): LiveData<List<BagItem>> {
        return bagItemDAO.getAllBagItems(currentDnDCharacter)
    }
}