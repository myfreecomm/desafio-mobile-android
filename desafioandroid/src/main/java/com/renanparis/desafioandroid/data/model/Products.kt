package com.renanparis.desafioandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity
data class Products(
        @PrimaryKey(autoGenerate = true)
        @Expose
        val id: Long = 0,
        val name: String = "",
        val quantity: Int = 0,
        val stock: Int = 0,
        val image_url: String = "",
        val price: Int = 0,
        val tax: Int = 0,
        val shipping: Int = 0,
        val description: String = ""
) {
}