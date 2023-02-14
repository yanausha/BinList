package com.example.binlist.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BinInfoDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun binInfoDao(): BinInfoDao

    companion object {

        private var instance: AppDatabase? = null
        private val LOOK = Any()
        private const val DB_NAME = "bin.db"

        fun getInstance(application: Application): AppDatabase {
            instance?.let {
                return it
            }
            synchronized(LOOK) {
                instance?.let {
                    return it
                }
                val database = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                instance = database
                return database
            }
        }
    }
}
