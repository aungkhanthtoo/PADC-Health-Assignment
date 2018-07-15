package me.portfolio.aungkhanthtoo.healthcare.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import me.portfolio.aungkhanthtoo.healthcare.data.vos.HealthcareInfo
import me.portfolio.aungkhanthtoo.healthcare.persistence.dao.HealthDao

@Database(entities = [HealthcareInfo::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun healthDao(): HealthDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "health.db")
                        .build()
            }
            return INSTANCE!!
        }
    }
}