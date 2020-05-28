package com.example.dndapp.ui.extra.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dndapp.ui.extra.notes.model.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesRoomDatabase : RoomDatabase() {

    abstract fun notesDAO(): NotesDAO

    companion object {
        private const val DATABASE_NAME = "NOTES_DATABASE"

        @Volatile
        private var INSTANCE: NotesRoomDatabase? = null

        fun getDatabase(context: Context): NotesRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(NotesRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(
                            context.applicationContext,
                            NotesRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    INSTANCE?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            database.notesDAO().insertNotes(Notes(""))
                                        }
                                    }
                                }
                            })
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}
