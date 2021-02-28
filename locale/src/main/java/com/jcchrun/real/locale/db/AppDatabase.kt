package com.jcchrun.real.locale.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jcchrun.real.locale.dao.FavouriteDao
import com.jcchrun.real.locale.entities.FavouriteEntity

@Database(entities = [FavouriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao

    companion object {
        val DATABASE_NAME = "real_state_table"
    }
}