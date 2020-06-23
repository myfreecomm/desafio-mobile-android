package com.nexaas.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nexaas.app.data.entity.CartItemPO

@Dao
interface CartItemDAO {

    @Query("SELECT * FROM cart")
    fun getCartItems(): List<CartItemPO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cartItem: CartItemPO)
}
