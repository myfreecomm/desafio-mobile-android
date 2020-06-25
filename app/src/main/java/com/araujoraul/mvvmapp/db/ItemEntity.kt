package com.araujoraul.mvvmapp.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "item_table")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id_column") val id: Long,
    @SerializedName("name")@ColumnInfo(name = "name_column") val name: String?,
    @SerializedName("quantity")@ColumnInfo(name = "quantity_column") val quantity: Int?,
    @SerializedName("stock")@ColumnInfo(name = "stock_column") val stock: Int?,
    @SerializedName("image_url")@ColumnInfo(name = "imageUrl_column") val imageUrl: String?,
    @SerializedName("price")@ColumnInfo(name = "price_column") val price: Int?,
    @SerializedName("tax")@ColumnInfo(name = "tax_column") val tax: Int?,
    @SerializedName("shipping")@ColumnInfo(name = "shipping_column") val shipping: Int?,
    @SerializedName("description")@ColumnInfo(name = "description_column") val description: String?
) : Parcelable