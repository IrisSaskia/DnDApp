package com.example.dndapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dndapp.database.characters.DnDCharacterDAO
import com.example.dndapp.model.DnDCharacter
import com.example.dndapp.model.stats.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DnDCharacter::class], version = 1, exportSchema = false)
abstract class DnDDatabase : RoomDatabase() {
    abstract fun dndCharacterDAO(): DnDCharacterDAO

    companion object {
        private const val DATABASE_NAME = "CHARACTERS_DATABASE"

        @Volatile
        private var INSTANCE: DnDDatabase? = null

        fun getDatabase(context: Context): DnDDatabase? {
            if (INSTANCE == null) {
                synchronized(DnDDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(context.applicationContext, DnDDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                INSTANCE?.let { database ->
                                    CoroutineScope(Dispatchers.IO).launch {
                                        database.dndCharacterDAO().insertDnDCharacter(
                                            DnDCharacter(
                                                //TODO: replace with resource
                                                1,
                                                "Test",
                                                'O',
                                                "Chaotic Testing",
                                                25,
                                                "Dit is een notitie",
                                                Strength(1, 14, 2, 2, 2),
                                                Dexterity(1, 10, 0, 0, 0, 0, 0),
                                                Constitution(1, 16, 3, 3),
                                                Intelligence(1, 10, 0, 0,2, 2, 0, 0, 2),
                                                Wisdom(1, 15, 2, 4, 2, 4, 2, 2, 2),
                                                Charisma(1, 10, 0, 2, 0, 0, 0, 2)
                                            )
                                        )
                                    }
                                }
                            }
                        }).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}