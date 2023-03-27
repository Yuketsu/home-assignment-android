package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.entities.ComicEntity

@Database(entities = [ComicEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ComicsDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao

    companion object {
        @Volatile
        private var INSTANCE: ComicsDatabase? = null

        fun getDatabase(appContext: Context): ComicsDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    appContext, ComicsDatabase::class.java,
                    ComicsDatabase::class.simpleName!!
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}