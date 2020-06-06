package com.example.dndapp.model.stats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.dndapp.model.DnDCharacter

@Entity(tableName = "wisdomTable", foreignKeys = [
    ForeignKey(
        entity = DnDCharacter::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterID"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Wisdom (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "characterID")
    override var characterID: Long? = null,

    @ColumnInfo(name = "stat")
    override var stat: Int,

    @ColumnInfo(name = "modifier")
    override var modifier: Int,

    @ColumnInfo(name = "save")
    override var save: Int,

    @ColumnInfo(name = "animalHandling")
    var animalHandling: Int,

    @ColumnInfo(name = "insight")
    var insight: Int,

    @ColumnInfo(name = "medicine")
    var medicine: Int,

    @ColumnInfo(name = "perception")
    var perception: Int,

    @ColumnInfo(name = "survival")
    var survival: Int
) : Stat()