package br.com.mpc.android_challenge.ui

import androidx.lifecycle.*
import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.utils.SAVED_ITEM

class MainViewModel(private val handle: SavedStateHandle) : ViewModel(), LifecycleObserver {
    lateinit var item: Item

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun saveItem() {
        handle.set(SAVED_ITEM, item)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun getItem() {
        if (handle.contains(SAVED_ITEM)) item = handle.get<Item>(SAVED_ITEM)!!
    }
}