package br.com.mpc.android_challenge.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*

Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

@Entity
data class Item (
	@PrimaryKey(autoGenerate = true) val id: Long,
	@ColumnInfo(name = "name") val name : String?,
	@ColumnInfo(name = "quantity") val quantity : Int?,
	@ColumnInfo(name = "stock") val stock : Int?,
	@ColumnInfo(name = "image_url") val image_url : String?,
	@ColumnInfo(name = "price") val price : Int?,
	@ColumnInfo(name = "tax") val tax : Int?,
	@ColumnInfo(name = "shipping") val shipping : Int?,
	@ColumnInfo(name = "description") val description : String?
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readLong(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeLong(id)
		parcel.writeString(name)
		parcel.writeValue(quantity)
		parcel.writeValue(stock)
		parcel.writeString(image_url)
		parcel.writeValue(price)
		parcel.writeValue(tax)
		parcel.writeValue(shipping)
		parcel.writeString(description)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Item> {
		override fun createFromParcel(parcel: Parcel): Item {
			return Item(parcel)
		}

		override fun newArray(size: Int): Array<Item?> {
			return arrayOfNulls(size)
		}
	}
}
