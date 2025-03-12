package com.ludito.test.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.ludito.test.data.local.dao.AddressDao
import com.ludito.test.data.local.entity.AddressEntity

/**
 * Created by: androdev
 * Date: 13-03-2025
 * Time: 12:00 AM
 * Email: Kjuraev.001@gmail.com
 */

@Database(
    entities = [AddressEntity::class],
    exportSchema = false,
    version = 1
)
abstract class LuditoDatabase : RoomDatabase() {
    abstract val addressDao: AddressDao

    companion object {
        private const val DATABASE_NAME = "Ludito_DB.db"

        fun getInstance(context: Context): LuditoDatabase {
            return databaseBuilder(context, LuditoDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}