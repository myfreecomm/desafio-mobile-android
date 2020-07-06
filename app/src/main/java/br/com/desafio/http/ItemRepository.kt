package br.com.desafio.http

import br.com.desafio.ui.model.Item

class ItemRepository(private val freeApiDataSource: ItemDataSource):  ItemDataSource {

    override fun listAllObjects(success: (List<Item>) -> Unit, failure: () -> Unit) {
        freeApiDataSource.listAllObjects(success, failure)
    }
}