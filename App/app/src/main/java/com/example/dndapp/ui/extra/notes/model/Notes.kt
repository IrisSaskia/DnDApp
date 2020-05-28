package com.example.dndapp.ui.extra.notes.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notesTable")
data class Notes (
    @ColumnInfo(name = "notesText")
    var text: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable