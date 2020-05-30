package com.example.dndapp.model.stats

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

//@Entity(tableName = "constitutionTable")
data class Constitution (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "stat")
    var stat: Int,

    @ColumnInfo(name = "modifier")
    var modifier: Int,

    @ColumnInfo(name = "save")
    var save: Int
)