package com.example.cartapp.viewmodel

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cartapp.CartConstants.Companion.PREFS_TIME
import com.example.cartapp.model.cartrepository.ItemModel
import com.example.cartapp.model.cartrepository.dao.CartDao
import com.example.cartapp.model.cartrepository.service.CartApiService
import com.example.cartapp.notification.NotificationsHelper
import com.example.cartapp.util.TimeCacheUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CartListViewModel(private val cartApiService: CartApiService,
                        private val cartDao: CartDao,
                        private val timeSharedPreferences: SharedPreferences,
                        private val notificationsHelper: NotificationsHelper,
                        private val timeCacheUtil: TimeCacheUtil
) : BaseViewModel() {


    val cart = MutableLiveData<List<ItemModel>>()
    val cartLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        timeCacheUtil.checkCacheDuration()
        val updatedTime = timeSharedPreferences.getLong(PREFS_TIME, 0)
        if (updatedTime != null && updatedTime != 0L && System.nanoTime() - updatedTime < timeCacheUtil.getUpdateTime()) {
            fetchFromDatabase()
        } else {
            fetchFromRemote()
        }
    }

    fun refreshBypassCache() {
        fetchFromRemote()
    }

    private fun fetchFromDatabase() {
        viewModelScope.launch {
            val cart = cartDao.getAllItems()
            cartRetrieved(cart)
        }
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            cartApiService.getCart()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ItemModel>>() {
                    override fun onSuccess(cart: List<ItemModel>) {
                        storeCartLocal(cart)
                        notificationsHelper.createNotification()
                    }

                    override fun onError(e: Throwable) {
                        cartLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private suspend fun cartRetrieved(itens: List<ItemModel>) {
        viewModelScope.launch {
            cart.value = itens
            cartLoadError.value = false
            loading.value = false
        }
    }

    private fun storeCartLocal(list: List<ItemModel>) {
        viewModelScope.launch {
            cartDao.deleteAllItens()
            val result = list.toTypedArray().let { cartDao.insertAll(*it) }
            var i = 0
            while (i < list.size) {
                list[i].id = result[i].toInt()
                i++
            }
            cartRetrieved(list)
        }

        //timeSharedPreferences.edit(commit = true) { putLong(PREFS_TIME, System.nanoTime()) }
    }
}