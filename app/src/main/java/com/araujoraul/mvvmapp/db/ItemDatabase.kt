package com.araujoraul.mvvmapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase(){

    abstract fun itemDao(): ItemDao

    private class ItemDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { database ->
                scope.launch {
                    val itemDao = database.itemDao()

                    //Delete all content here
                    itemDao.deleteAll()

                    //Add data here TODO()


                }
            }

        }

    }

    companion object {

        private var INSTANCE: ItemDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ItemDatabase {

            //if the INSTANCE is not null, then return it
            //if it is, then create the database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, ItemDatabase::class.java,
                    "item_database"
                )
                    .addCallback(
                        ItemDatabaseCallback(scope)
                    ).build()
                INSTANCE = instance

                //return instance
                instance
            }

        }

    }

}