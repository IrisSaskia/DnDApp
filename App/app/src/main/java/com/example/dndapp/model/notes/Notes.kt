package com.example.dndapp.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class Notes (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "notesText") //TODO: Change name to Note
    var text: String
)

/*
@Entity(foreignKeys = [ForeignKey(entity = Characters::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("noteID"),
    onDelete = ForeignKey.CASCADE)])*/


//TODO: remove notes so it is in the character class without reference to this table