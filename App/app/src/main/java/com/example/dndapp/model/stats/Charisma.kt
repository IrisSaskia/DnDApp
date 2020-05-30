package com.example.dndapp.model.stats

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

//@Entity(tableName = "charismaTable")
data class Charisma (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "stat")
    var stat: Int,

    @ColumnInfo(name = "modifier")
    var modifier: Int,

    @ColumnInfo(name = "save")
    var save: Int,

    @ColumnInfo(name = "deception")
    var deception: Int,

    @ColumnInfo(name = "intimidation")
    var intimidation: Int,

    @ColumnInfo(name = "performance")
    var performance: Int,

    @ColumnInfo(name = "persuasion")
    var persuasion: Int
)