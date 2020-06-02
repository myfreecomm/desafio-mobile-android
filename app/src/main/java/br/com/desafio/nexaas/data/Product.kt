package br.com.desafio.nexaas.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Product(
    @PrimaryKey
    val name: String,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val price: Int,
    val quantity: Int,
    val shipping: Int,
    val stock: Int,
    val tax: Int
): Parcelable