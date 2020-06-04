package com.nexaas.desafio.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Products")
@Parcelize
data class Product (@PrimaryKey(autoGenerate = true) val id: Int,
                    val name: String,
                    val quantity: Int,
                    val stock: Int,
                    @SerializedName("image_url")
                    val imageUrl: String,
                    val price: Double,
                    val tax: Double,
                    val shipping: Int,
                    val description: String) : Parcelable