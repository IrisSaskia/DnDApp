package com.example.dndapp.model.stats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dexterityTable")
data class Dexterity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

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