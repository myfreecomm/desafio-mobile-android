package br.com.mpc.android_challenge.repository.local

import br.com.mpc.android_challenge.db.NexaasDao
import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.utils.Event
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: NexaasDao) {

    fun getItems(): Flow<Event<ArrayList<Item>>> = dao.getItemUntilChanged()
    fun deleteItems() = dao.deleteAll()
    fun setItems(items: List<Item>) = dao.setItems(items)
}