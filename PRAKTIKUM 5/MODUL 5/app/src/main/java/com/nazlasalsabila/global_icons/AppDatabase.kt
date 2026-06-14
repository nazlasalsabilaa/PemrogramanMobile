package com.nazlasalsabila.global_icons

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GlobalIcon::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun globalIconDao(): GlobalIconDao
}