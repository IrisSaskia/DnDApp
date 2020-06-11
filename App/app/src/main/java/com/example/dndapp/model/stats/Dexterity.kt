package com.example.dndapp.model.stats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.dndapp.model.DnDCharacter

//Foreign key reference to the character table
@Entity(tableName = "dexterityTable", foreignKeys = [
    ForeignKey(
        entity = DnDCharacter::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterID"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Dexterity (
    @ColumnInfo(name = "stat")
    override var stat: Int,

    @ColumnInfo(name = "modifier")
    override var modifier: Int,

    @ColumnInfo(name = "save")
    override var save: Int,

    @ColumnInfo(name = "acrobatics")
    var acrobatics: Int,

    @ColumnInfo(name = "sleightOfHand")
    var sleightOfHand: Int,

    @ColumnInfo(name = "stealth")
    var stealth: Int,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "characterID")
    override var characterID: Long? = null
) : Stat()