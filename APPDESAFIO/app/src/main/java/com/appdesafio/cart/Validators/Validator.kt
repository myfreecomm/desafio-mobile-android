package com.appdesafio.cart.Validators

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData


open class Validator<T> : MutableLiveData<T>() {
    companion object {
        private val TAG = "Validator"
    }
    private val live = AtomicBoolean(false)
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Erro Observe MultiLivedata")
        }
        super.observe(owner, Observer { t ->
            if (live.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }
    @MainThread
    override fun setValue(t: T?) {
        live.set(true)
        super.setValue(t)
    }
    @MainThread
    fun call() {
        value = null
    }
}