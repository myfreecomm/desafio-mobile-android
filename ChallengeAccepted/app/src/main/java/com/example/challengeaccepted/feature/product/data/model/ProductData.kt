package com.example.challengeaccepted.feature.product.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product")
data class ProductData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val quantity: Int,
    val stock: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val price: Long,
    val tax: Long,
    val shipping: Long,
    val description: String
) : Parcelable