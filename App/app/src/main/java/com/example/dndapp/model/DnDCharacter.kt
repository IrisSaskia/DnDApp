package com.example.dndapp.model

import androidx.room.*
import com.example.dndapp.model.stats.*

@Entity(tableName = "dndCharacterTable",
    foreignKeys = [
        ForeignKey(
            entity = Strength::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("strengthID"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Dexterity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("dexterityID"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Constitution::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("constitutionID"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Intelligence::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("intelligenceID"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Wisdom::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("wisdomID"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Charisma::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("charismaID"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
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

    @ColumnInfo(name = "strengthID")
    var strengthID: Long?,

    @ColumnInfo(name = "dexterityID")
    var dexterityID: Long?,

    @ColumnInfo(name = "constitutionID")
    var constitutionID: Long?,

    @ColumnInfo(name = "intelligenceID")
    var intelligenceID: Long?,

    @ColumnInfo(name = "wisdomID")
    var wisdomID: Long?,

    @ColumnInfo(name = "charismaID")
    var charismaID: Long?
)