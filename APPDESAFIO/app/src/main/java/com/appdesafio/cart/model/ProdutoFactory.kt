package com.appdesafio.cart.model
import androidx.lifecycle.*

class ProdutoFactory( private val repo: Repositorio ) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?>
            create(modelClass: Class<T>): T {
        return ProdutoModel(repo) as T
    }
}
