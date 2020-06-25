package com.gui.antonio.testenexaas.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.text.NumberFormat
import java.util.*

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("name") @ColumnInfo(name = "name") val name: String,
    @SerializedName("quantity") @ColumnInfo(name = "quantity") val quantity: Int,
    @SerializedName("stock") @ColumnInfo(name = "stock") val stock: Int,
    @SerializedName("image_url") @ColumnInfo(name = "image_url") val image_url: String,
    @SerializedName("price") @ColumnInfo(name = "price") val price: Double,
    @SerializedName("tax") @ColumnInfo(name = "tax") val tax: Int,
    @SerializedName("shipping") @ColumnInfo(name = "shipping") val shipping: Int,
    @SerializedName("description") @ColumnInfo(name = "description") val description: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString().toString(),
        source.readInt(),
        source.readInt(),
        source.readString().toString(),
        source.readDouble(),
        source.readInt(),
        source.readInt(),
        source.readString().toString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(name)
        writeInt(quantity)
        writeInt(stock)
        writeString(image_url)
        writeDouble(price)
        writeInt(tax)
        writeInt(shipping)
        writeString(description)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ProductEntity> =
            object : Parcelable.Creator<ProductEntity> {
                override fun createFromParcel(source: Parcel): ProductEntity = ProductEntity(source)
                override fun newArray(size: Int): Array<ProductEntity?> = arrayOfNulls(size)
            }
    }


}