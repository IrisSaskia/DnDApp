package com.example.dndapp.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Parcelize
@Entity(tableName = "notesTable")
data class Notes (
    @ColumnInfo(name = "notesText")
    var text: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) //: Parcelable