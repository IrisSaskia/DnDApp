package com.example.dndapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dndapp.model.stats.*

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
    var note: String,

    @ColumnInfo(name = "strength")
    @Embedded
    var strength: Strength,

    @ColumnInfo(name = "dexterity")
    @Embedded
    var dexterity: Dexterity,

    @ColumnInfo(name = "constitution")
    @Embedded
    var constitution: Constitution,

    @ColumnInfo(name = "intelligence")
    @Embedded
    var intelligence: Intelligence,

    @ColumnInfo(name = "wisdom")
    @Embedded
    var wisdom: Wisdom,

    @ColumnInfo(name = "charisma")
    @Embedded
    var charisma: Charisma
)