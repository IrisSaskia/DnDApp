package com.example.dndapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dndapp.database.bagItems.BagItemDAO
import com.example.dndapp.database.characters.DnDCharacterDAO
import com.example.dndapp.database.stats.StatsDAO
import com.example.dndapp.model.BagItem
import com.example.dndapp.model.DnDCharacter
import com.example.dndapp.model.Money
import com.example.dndapp.model.stats.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DnDCharacter::class,
                        BagItem::class,
                        Money::class,
                        Strength::class,
                        Dexterity::class,
                        Constitution::class,
                        Intelligence::class,
                        Wisdom::class,
                        Charisma::class], version = 3, exportSchema = false)
abstract class DnDDatabase : RoomDatabase() {
    //All room daos
    abstract fun dndCharacterDAO(): DnDCharacterDAO
    abstract fun bagItemDAO(): BagItemDAO
    abstract fun statsDAO(): StatsDAO

    companion object {
        private const val DATABASE_NAME = "CHARACTERS_DATABASE"

        @Volatile
        private var INSTANCE: DnDDatabase? = null

        fun getDatabase(context: Context): DnDDatabase? {
            //Build the database if it does not exist already
            if (INSTANCE == null) {
                synchronized(DnDDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(context.applicationContext, DnDDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                INSTANCE?.let { database ->
                                    //Insert some example characters into the database
                                    CoroutineScope(Dispatchers.IO).launch {
                                        database.dndCharacterDAO().insertDnDCharacter(
                                            DnDCharacter(
                                                //TODO: replace with resource
                                                false,
                                                "Test",
                                                "Other",
                                                "Chaotic Testing",
                                                25,
                                                "Dit is een notitie",
                                                "Acolyte",
                                                "Dwarf",
                                                "Hill",
                                                "Barbarian",
                                                1
                                            )
                                        )
                                        database.statsDAO().insertStrength(Strength(4, 2, 2, 2, 1))
                                        database.statsDAO().insertDexterity(Dexterity(10, 0, 0, 0, 0, 0, 1))
                                        database.statsDAO().insertConstitution(Constitution(16, 3, 3, 1))
                                        database.statsDAO().insertIntelligence(Intelligence(10, 0, 0,2, 2, 0, 0, 2, 1))
                                        database.statsDAO().insertWisdom(Wisdom(15, 2, 4, 2, 4, 2, 2, 2, 1))
                                        database.statsDAO().insertCharisma(Charisma(10, 0, 2, 0, 0, 0, 2, 1))

                                        database.bagItemDAO().insertBagItem(BagItem(1, "Caltrops", 5, 1))
                                        database.bagItemDAO().insertBagItem(BagItem(1, "Shield", 25, 1))
                                        database.bagItemDAO().insertBagItem(BagItem(10, "Ball bearings", 10, 1))
                                        database.bagItemDAO().insertBagItem(BagItem(1, "Dagger", 10, 1))

                                        database.bagItemDAO().insertMoney(Money(10, 5, 1, 12, 5, 1))

                                        database.dndCharacterDAO().insertDnDCharacter(
                                            DnDCharacter(
                                                //TODO: replace with resource
                                                true,
                                                "Test 2",
                                                "Female",
                                                "Chaotic Testing",
                                                20,
                                                "Dit is een notitieee",
                                                "Scoundrel",
                                                "Dragonborn",
                                                "",
                                                "Bard",
                                                2
                                            )
                                        )
                                        database.statsDAO().insertStrength(Strength(1, 2, 2, 2, 2))
                                        database.statsDAO().insertDexterity(Dexterity(2, 0, 0, 0, 0, 0, 2))
                                        database.statsDAO().insertConstitution(Constitution(3, 3, 3, 2))
                                        database.statsDAO().insertIntelligence(Intelligence(4, 0, 0,2, 2, 0, 0, 2, 2))
                                        database.statsDAO().insertWisdom(Wisdom(5, 2, 4, 2, 4, 2, 2, 2, 2))
                                        database.statsDAO().insertCharisma(Charisma(6, 0, 2, 0, 0, 0, 2, 2))

                                        database.bagItemDAO().insertBagItem(BagItem(2, "Potion of healing", 12, 2))
                                        database.bagItemDAO().insertBagItem(BagItem(1, "Plate armor", 50, 2))
                                        database.bagItemDAO().insertBagItem(BagItem(8, "Rations", 40, 2))

                                        database.bagItemDAO().insertMoney(Money(0, 45, 10, 2, 5, 2))
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