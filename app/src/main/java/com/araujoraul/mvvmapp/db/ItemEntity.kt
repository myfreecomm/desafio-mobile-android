package com.araujoraul.mvvmapp.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.araujoraul.mvvmapp.data.model.Item
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item_table")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @Embedded val item: Item
)