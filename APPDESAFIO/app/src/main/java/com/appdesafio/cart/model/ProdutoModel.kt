package com.appdesafio.cart.model
import com.appdesafio.cart.Validators.Validator
import androidx.lifecycle.*

import android.util.Log

private const val TAG = "ERRO AO BUSCAR PRODUTOS"

class ProdutoModel(private val repo: Repositorio) : ViewModel() {
    var onLoadFinished = Validator<Void>()
    var onError = Validator<String>()
    var prod: MutableLiveData<List<Produto>> = MutableLiveData()
    fun getProdutos() {
        repo.getProdutos(
            onSuccess = { data ->
                this.prod.value = data
                onLoadFinished.call()
            },
            onError = { erro ->
                onError.value = erro
                onError.call()
                onLoadFinished.call()
                Log.e(TAG, "Ocorreu erro inesperado ao buscar produtos")
            },
            showOfflineProducts = { off ->
                prod.value = off
            }
        )
    }
}
