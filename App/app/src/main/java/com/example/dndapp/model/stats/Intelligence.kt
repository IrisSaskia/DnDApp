package com.example.dndapp.model.stats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.dndapp.model.DnDCharacter

//Foreign key reference to the character table
@Entity(tableName = "intelligenceTable", foreignKeys = [
    ForeignKey(
        entity = DnDCharacter::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterID"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Intelligence (
    @ColumnInfo(name = "stat")
    override var stat: Int,

    @ColumnInfo(name = "modifier")
    override var modifier: Int,

    @ColumnInfo(name = "save")
    override var save: Int,

    @ColumnInfo(name = "arcana")
    var arcana: Int,

    @ColumnInfo(name = "history")
    var history: Int,

    @ColumnInfo(name = "investigation")
    var investigation: Int,

    @ColumnInfo(name = "nature")
    var nature: Int,

    @ColumnInfo(name = "religion")
    var religion: Int,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "characterID")
    override var characterID: Long? = null
) : Stat()