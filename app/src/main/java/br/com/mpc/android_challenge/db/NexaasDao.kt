package br.com.mpc.android_challenge.db

import androidx.room.Query
import br.com.mpc.android_challenge.models.Item
import kotlinx.coroutines.flow.Flow

interface NexaasDao {

    @Query("SELECT * FROM item")
    fun getItems() : Flow<ArrayList<Item>>
}