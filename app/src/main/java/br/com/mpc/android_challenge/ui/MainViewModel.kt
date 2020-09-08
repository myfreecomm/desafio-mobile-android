package br.com.mpc.android_challenge.ui

import androidx.lifecycle.*
import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.utils.SAVED_ITEM
import timber.log.Timber

class MainViewModel(private val handle: SavedStateHandle) : ViewModel(), LifecycleObserver {
    var item: Item? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun saveItem() {
        println("saveItem")
        handle.set(SAVED_ITEM, item)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun getItem() {
        item = handle.get<Item>(SAVED_ITEM)
    }
}