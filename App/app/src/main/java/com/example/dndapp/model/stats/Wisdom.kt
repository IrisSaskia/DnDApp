package com.example.dndapp.model.stats

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

//@Entity(tableName = "wisdomTable")
data class Wisdom (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "stat")
    var stat: Int,

    @ColumnInfo(name = "modifier")
    var modifier: Int,

    @ColumnInfo(name = "save")
    var save: Int,

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
)