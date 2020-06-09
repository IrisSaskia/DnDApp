package com.example.dndapp.database.bagItems

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.dndapp.database.DnDDatabase
import com.example.dndapp.model.BagItem

class BagItemRepository(context: Context) {
    private val bagItemDAO: BagItemDAO

    init {
        val database = DnDDatabase.getDatabase((context))
        bagItemDAO = database!!.bagItemDAO()
    }

    suspend fun insertBagItem(bagItem: BagItem) {
        bagItemDAO.insertBagItem(bagItem)
    }

    fun getAllBagItems(currentDnDCharacter: Int): LiveData<List<BagItem>> {
        return bagItemDAO.getAllBagItems(currentDnDCharacter)
    }
}