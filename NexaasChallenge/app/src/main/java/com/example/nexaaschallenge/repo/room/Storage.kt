package com.example.nexaaschallenge.repo.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nexaaschallenge.repo.ItemCart

@Database(entities = arrayOf(ItemCart::class), version = 1, exportSchema = false)
abstract class Storage : RoomDatabase() { abstract val dao: Dao }
