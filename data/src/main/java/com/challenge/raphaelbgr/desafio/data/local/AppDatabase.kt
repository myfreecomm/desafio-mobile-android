package com.challenge.raphaelbgr.desafio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.challenge.raphaelbgr.desafio.data.local.converters.Converters
import com.challenge.raphaelbgr.desafio.data.local.entities.HighlightEntity
import com.challenge.raphaelbgr.desafio.data.local.entities.MatchEntity
import com.challenge.raphaelbgr.desafio.data.local.entities.TeamEntity

@Database(entities = [MatchEntity::class, TeamEntity::class, HighlightEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun matchDao(): MatchDao
    abstract fun teamDao(): TeamDao
    abstract fun highlightDao(): HighlightDao
}