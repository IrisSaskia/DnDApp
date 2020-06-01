package com.example.dndapp.model.stats

/*
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.dndapp.model.DnDCharacter

@Entity(tableName = "statsTable",
    foreignKeys = [
        ForeignKey(
          entity = DnDCharacter::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id"),
            onDelete = ForeignKey.CASCADE
        ),
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
data class Stats (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long? = null,

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
)*/
