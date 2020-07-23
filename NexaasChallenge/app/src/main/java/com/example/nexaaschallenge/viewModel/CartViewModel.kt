package com.example.nexaaschallenge.viewModel

import android.content.Context
import androidx.lifecycle.*
import com.example.nexaaschallenge.model.Prices
import com.example.nexaaschallenge.repo.ItemCart
import com.example.nexaaschallenge.repo.Reachability
import com.example.nexaaschallenge.repo.Repo
import com.example.nexaaschallenge.repo.retrofit.Resource
import com.example.nexaaschallenge.repo.retrofit.Status.*
import com.example.nexaaschallenge.repo.room.Dao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(private val repo: Repo,
                    private val dao: Dao
) : ViewModel() {

    val cart = MutableLiveData<List<ItemCart>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val networkAvailable = MutableLiveData<Boolean>()
    val cartResource = liveData(Dispatchers.IO) {
        if (networkAvailable.value!!) {
            emit(Resource.loading(null))
            emit(repo.getCart())
        } else {
            fetchLocal()
        }
    }

    private val cartObserver = Observer<Resource<List<ItemCart>>> {
        when (it.status) {
            SUCCESS -> saveLocally(it.data!!)
            ERROR -> fetchLocal()
            LOADING -> loading.value = true
        }
    }

    fun checkConnection(context: Context) {
        Thread {
            Reachability.isInternetConnected(context).also {
                viewModelScope.launch { withContext(Dispatchers.Main) {
                    networkAvailable.value = it
                }}
            }
        }.start()
    }

    fun fetchCart(owner: LifecycleOwner) {
        if (networkAvailable.value!!) {
            cartResource.observe(owner, cartObserver)
        } else {
            fetchLocal()
        }
    }

    private fun calculatePrice() = cart.value?.run { map { it.price }.sum() } ?: 0f

    private fun calculateShipping() = cart.value?.run { map { it.shipping }.sum() } ?: 0f

    private fun calculateTax() = cart.value?.run { map { it.tax }.sum() } ?: 0f

    fun getPrices(): Prices {
        val totalPrice = calculatePrice()
        val totalShipping = calculateShipping()
        val totalTax = calculateTax()
        val total = totalPrice + totalShipping + totalTax
        return Prices(
            totalPrice,
            totalShipping,
            totalTax,
            total
        )
    }

    private fun fetchLocal() {
        viewModelScope.launch { setCart(dao.getItems()) }
    }

    private fun setCart(itens: List<ItemCart>) {
        if (itens.isNullOrEmpty()) {
            error.value = true
        } else {
            viewModelScope.launch { cart.value = itens }
        }
        loading.value = false
    }

    private fun saveLocally(items: List<ItemCart>) {
        viewModelScope.launch {
            dao.deleteItens()
            dao.insertItems(items)
            setCart(items)
        }
    }
}