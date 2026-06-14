package com.nazlasalsabila.global_icons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GlobalIconDao {
    @Query("SELECT * FROM movie_explorer")
    suspend fun getAllIcons(): List<GlobalIcon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(icons: List<GlobalIcon>)

    @Query("DELETE FROM movie_explorer")
    suspend fun deleteAll()
}