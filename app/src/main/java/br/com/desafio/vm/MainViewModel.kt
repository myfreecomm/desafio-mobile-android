package br.com.desafio.vm

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import br.com.desafio.R
import br.com.desafio.ui.model.Item
import br.com.desafio.http.ItemDataSource

class MainViewModel(private val repository: ItemDataSource, private val application: Application) : ViewModel(), LifecycleObserver, View.OnClickListener {

    var itens = MutableLiveData<List<Item>>()
    val showLoading = ObservableBoolean(false)
    val msg = ObservableField<String>()
    var contador = ObservableField<String>()
    var valorTotal = MutableLiveData<String>()
    var valorSubtotal = MutableLiveData<String>()
    var valorShipping = MutableLiveData<String>()
    var valorTax = MutableLiveData<String>()

    fun toLoad() {
        showLoading.set(true)
        msg.set("")
        repository.listAllObjects({ items ->
            itens.postValue(items)
            if (items.isEmpty()) {
                msg.set(application.getString(R.string.empty))
            } else {
                contador.set(items.size.toString() + " itens no carrinho!")
                somar(items)
                showLoading.set(false)
            }
        }, {
            msg.set(application.getString(R.string.failed))
            showLoading.set(false)
        })
    }

    private fun somar(itens: List<Item>) {
        var totalPrice = itens.sumByDouble { it.price }
        var totalTax = itens.sumByDouble { it.tax }
        var totalShip = itens.sumBy { it.shipping }
        valorTotal.value = "R$ ${totalPrice.plus(totalTax)}"
        valorSubtotal.value = "R$ $totalPrice"
        valorShipping.value = totalShip.toString()
        valorTax.value = "R$ $totalTax"
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btcheckout -> {Toast.makeText(application.applicationContext, "Check Out", Toast.LENGTH_SHORT).show()}
            else -> {Toast.makeText(application.applicationContext, "XXXX", Toast.LENGTH_SHORT).show()}
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        Log.d("onResume", "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d("onPause", "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d("onStart", "onStart")
        toLoad()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("onCreate", "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d("onStop", "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.d("onDestroy", "onDestroy")
    }
}