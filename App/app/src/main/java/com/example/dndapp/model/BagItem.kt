package com.example.dndapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "bagItemTable", foreignKeys = [
    ForeignKey(
        entity = DnDCharacter::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterID"),
        onDelete = ForeignKey.CASCADE
    )
])
data class BagItem (
    @ColumnInfo(name = "amount")
    var amount: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "weight")
    var weight: Int,

    @ColumnInfo(name = "characterID")
    var characterID: Long? = null,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)