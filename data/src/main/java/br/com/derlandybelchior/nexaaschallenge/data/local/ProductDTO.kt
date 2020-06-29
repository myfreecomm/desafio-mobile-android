package br.com.derlandybelchior.nexaaschallenge.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.derlandybelchior.nexaaschallenge.data.local.COLUMN_ID
import br.com.derlandybelchior.nexaaschallenge.data.local.TABLE_PRODUCTS

@Entity(tableName = TABLE_PRODUCTS)
data class ProductDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = COLUMN_ID)
    val name: String,
    val quantity: Int,
    val stock: Int,
    val image: String,
    val price: Double,
    val tax: Double,
    val shipping: Double,
    val description: String
)