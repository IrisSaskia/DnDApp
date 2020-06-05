package com.example.dndapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dndapp.database.characters.DnDCharacterDAO
import com.example.dndapp.database.stats.StatsDAO
import com.example.dndapp.model.DnDCharacter
import com.example.dndapp.model.stats.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DnDCharacter::class,
                        Strength::class,
                        Dexterity::class,
                        Constitution::class,
                        Intelligence::class,
                        Wisdom::class,
                        Charisma::class], version = 1, exportSchema = false)
abstract class DnDDatabase : RoomDatabase() {
    abstract fun dndCharacterDAO(): DnDCharacterDAO
    abstract fun statsDAO(): StatsDAO

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
                                                false,
                                                "Test",
                                                'O',
                                                "Chaotic Testing",
                                                25,
                                                "Dit is een notitie",
                                                "Acolyte",
                                                "Dwarf",
                                                "Hill"
                                            )
                                        )
                                        database.statsDAO().insertStrength(Strength(1, 14, 2, 2, 2))
                                        database.statsDAO().insertDexterity(Dexterity(1, 10, 0, 0, 0, 0, 0))
                                        database.statsDAO().insertConstitution(Constitution(1, 16, 3, 3))
                                        database.statsDAO().insertIntelligence(Intelligence(1, 10, 0, 0,2, 2, 0, 0, 2))
                                        database.statsDAO().insertWisdom(Wisdom(1, 15, 2, 4, 2, 4, 2, 2, 2))
                                        database.statsDAO().insertCharisma(Charisma(1, 10, 0, 2, 0, 0, 0, 2))
                                        database.dndCharacterDAO().insertDnDCharacter(
                                            DnDCharacter(
                                                //TODO: replace with resource
                                                2,
                                                true,
                                                "Test 2",
                                                'V',
                                                "Chaotic Testing",
                                                20,
                                                "Dit is een notitieee",
                                                "Scoundrel",
                                                "Dragonborn",
                                                ""
                                            )
                                        )
                                        database.statsDAO().insertStrength(Strength(2, 1, 2, 2, 2))
                                        database.statsDAO().insertDexterity(Dexterity(2, 2, 0, 0, 0, 0, 0))
                                        database.statsDAO().insertConstitution(Constitution(2, 3, 3, 3))
                                        database.statsDAO().insertIntelligence(Intelligence(2, 4, 0, 0,2, 2, 0, 0, 2))
                                        database.statsDAO().insertWisdom(Wisdom(2, 5, 2, 4, 2, 4, 2, 2, 2))
                                        database.statsDAO().insertCharisma(Charisma(2, 6, 0, 2, 0, 0, 0, 2))
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