package com.jcchrun.real.locale.di

import android.content.Context
import androidx.room.Room
import com.jcchrun.real.locale.db.AppDatabase
import org.koin.dsl.module

val localeModule = module {

    single { createDatabase(get()) }
    factory { (get() as AppDatabase).favouriteDao() }

}

private fun createDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
}