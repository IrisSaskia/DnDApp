package com.example.dndapp.model

import androidx.annotation.Nullable
import androidx.room.*
import com.example.dndapp.model.stats.*

@Entity(tableName = "dndCharacterTable")
data class DnDCharacter (
    @ColumnInfo(name = "active")
    var active: Boolean,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "sex")
    var sex: Char,

    @ColumnInfo(name = "alignment")
    var alignment: String,

    @ColumnInfo(name = "level")
    var level: Int,

    @ColumnInfo(name = "note")
    var note: String,

    @ColumnInfo(name = "background")
    var background: String,

    @ColumnInfo(name = "race")
    var race: String,

    //TODO: Test the Nullable thing
    @ColumnInfo(name = "subRace")
    @Nullable
    var subRace: String,

    //TODO: Add multiclassing
    @ColumnInfo(name = "cclass")
    var cclass: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)