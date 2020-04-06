package com.globo.raphaelbgr.desafio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globo.raphaelbgr.desafio.data.local.entities.MatchEntity
import com.globo.raphaelbgr.desafio.data.local.entities.TeamEntity

@Database(entities = [MatchEntity::class, TeamEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun matchDao(): MatchDao
    abstract fun teamDao(): TeamDao
}