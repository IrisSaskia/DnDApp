package com.example.dndapp.model.stats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.dndapp.model.DnDCharacter

@Entity(tableName = "dexterityTable", foreignKeys = [
    ForeignKey(
        entity = DnDCharacter::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterID"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Dexterity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "characterID")
    var characterID: Long? = null,

    @ColumnInfo(name = "stat")
    var stat: Int,

    @ColumnInfo(name = "modifier")
    var modifier: Int,

    @ColumnInfo(name = "save")
    var save: Int,

    @ColumnInfo(name = "acrobatics")
    var acrobatics: Int,

    @ColumnInfo(name = "sleightOfHand")
    var sleightOfHand: Int,

    @ColumnInfo(name = "stealth")
    var stealth: Int
)