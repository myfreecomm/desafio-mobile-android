package com.appdesafio.cart.model
import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.*
import com.appdesafio.cart.services.Network
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class Repositorio(
    private val db: DaoProd
) {
    @SuppressLint("CheckResult")
    private fun getProdutosDB(
        onSuccess: (produtos: List<Produto>) -> Unit,
        onError: (error: String) -> Unit
    ) {
        db.getProdutos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                onSuccess(data)
            }, {
                onError(it.message.toString())
            })
       }

    private fun update(produtos: List<Produto>) {
        Completable.fromAction {
            db.refatorarBD(produtos)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    Log.i("Salva Produto Local", "Produto Salvo")
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onError(e: Throwable) {
                }
            })
    }

    fun getProdutos(
        showOfflineProducts: (produtos: List<Produto>) -> Unit,
        onSuccess: (produtos: List<Produto>) -> Unit,
        onError: (error: String) -> Unit
    ) {
        Network.getProdutosService(
            onSuccess = { productsResponse ->
                update(productsResponse)
                getProdutosDB(onSuccess, onError)
            },
            onError = { erro ->
                onError(erro.message.toString())
                getProdutosDB(onSuccess = {
                    showOfflineProducts(it)
                }, onError = {
                })
            }
        )
    }
}