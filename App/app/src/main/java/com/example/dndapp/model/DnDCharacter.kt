package com.example.dndapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dndCharacterTable")
data class DnDCharacter (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "sex")
    var sex: Char,

    @ColumnInfo(name = "alignment")
    var alignment: String,

    @ColumnInfo(name = "level")
    var level: Int,

    @ColumnInfo(name = "note")
    var note: String
)