package br.com.mpc.android_challenge.db

import androidx.room.*
import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.utils.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface NexaasDao {

    @Query("SELECT * FROM item")
    abstract fun getItems(): Flow<List<Item>>

    @Insert(entity = Item::class, onConflict = OnConflictStrategy.REPLACE)
    fun setItems(items: List<Item>)

    @Query("DELETE FROM item")
    fun deleteAll()

    fun getItemUntilChanged(): Flow<Event<ArrayList<Item>>> =
        getItems().map { return@map Event<ArrayList<Item>>().apply { content = ArrayList(it) } }
}
