package br.com.desafio.http

import br.com.desafio.ui.model.Item

interface ItemDataSource {

    fun listAllObjects(success : (List<Item>) -> Unit, failure: () -> Unit)
}