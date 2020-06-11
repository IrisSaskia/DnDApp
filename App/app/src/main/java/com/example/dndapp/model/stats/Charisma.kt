package com.example.dndapp.model.stats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.dndapp.model.DnDCharacter

//Foreign key reference to the character table
@Entity(tableName = "charismaTable", foreignKeys = [
    ForeignKey(
        entity = DnDCharacter::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterID"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Charisma (
    @ColumnInfo(name = "stat")
    override var stat: Int,

    @ColumnInfo(name = "modifier")
    override var modifier: Int,

    @ColumnInfo(name = "save")
    override var save: Int,

    @ColumnInfo(name = "deception")
    var deception: Int,

    @ColumnInfo(name = "intimidation")
    var intimidation: Int,

    @ColumnInfo(name = "performance")
    var performance: Int,

    @ColumnInfo(name = "persuasion")
    var persuasion: Int,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "characterID")
    override var characterID: Long? = null
) : Stat()