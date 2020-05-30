package com.example.dndapp.model.stats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "intelligenceTable")
data class Intelligence (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "stat")
    var stat: Int,

    @ColumnInfo(name = "modifier")
    var modifier: Int,

    @ColumnInfo(name = "save")
    var save: Int,

    @ColumnInfo(name = "arcana")
    var arcana: Int,

    @ColumnInfo(name = "history")
    var history: Int,

    @ColumnInfo(name = "investigation")
    var investigation: Int,

    @ColumnInfo(name = "nature")
    var nature: Int,

    @ColumnInfo(name = "religion")
    var religion: Int
)