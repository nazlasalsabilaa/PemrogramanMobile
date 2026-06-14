package com.nazlasalsabila.global_icons

import android.content.Context
import androidx.room.Room

object Injection {
    fun provideRepository(context: Context, lang: String): GlobalIconRepository {
        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "global_icon_db"
        ).fallbackToDestructiveMigration().build()

        return GlobalIconRepository(TmdbApi.instance, db.globalIconDao(), lang)
    }
}