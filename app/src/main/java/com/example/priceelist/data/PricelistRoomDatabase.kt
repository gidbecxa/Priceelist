package com.example.priceelist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pricelist::class], version = 7, exportSchema = false)
abstract class PricelistRoomDatabase: RoomDatabase() {

    abstract fun pricelistDao(): PricelistDao

    companion object {
        @Volatile
        private var INSTANCE: PricelistRoomDatabase? = null

        fun getDatabase(context: Context): PricelistRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PricelistRoomDatabase::class.java,
                    "pricelist_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}