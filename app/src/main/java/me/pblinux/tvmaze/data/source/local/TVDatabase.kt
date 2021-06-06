package me.pblinux.tvmaze.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.pblinux.tvmaze.data.models.show.Show

@Database(entities = [Show::class], version = 1)
@TypeConverters(Converters::class)
abstract class TVDatabase : RoomDatabase() {
    abstract fun showDao(): TVDao
}