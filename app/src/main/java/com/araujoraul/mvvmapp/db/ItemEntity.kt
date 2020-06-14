package com.araujoraul.mvvmapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey private val id: Int,
    private val name: String,
    private val quantity: Int,
    private val stock: Int,
    private val image_url: String,
    private val price: Int,
    private val tax: Int,
    private val shipping: Int,
    private val description: String

)