package me.pblinux.tvmaze.data.source.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.pblinux.tvmaze.data.models.show.Show

@Dao
interface TVDao {
    @Query("SELECT * FROM show")
    fun getAll(): Flow<List<Show>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(vararg shows: Show)

    @Delete
    suspend fun remove(show: Show)
}