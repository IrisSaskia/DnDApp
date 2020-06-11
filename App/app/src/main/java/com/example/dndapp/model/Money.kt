package com.example.dndapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "moneyTable", foreignKeys = [
    ForeignKey(
        entity = DnDCharacter::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterID"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Money (
    @ColumnInfo(name = "amountOfPlatinum")
    var amountOfPlatinum: Int,

    @ColumnInfo(name = "amountOfGold")
    var amountOfGold: Int,

    @ColumnInfo(name = "amountOfElectrum")
    var amountOfElectrum: Int,

    @ColumnInfo(name = "amountOfSilver")
    var amountOfSilver: Int,

    @ColumnInfo(name = "amountOfCopper")
    var amountOfCopper: Int,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "characterID")
    var characterID: Long? = null
)