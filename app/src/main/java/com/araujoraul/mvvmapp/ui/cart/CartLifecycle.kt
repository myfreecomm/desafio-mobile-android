package com.araujoraul.mvvmapp.ui.cart

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class CartLifecycle(val application: Application) : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getOnResume() {
        Toast.makeText(application.applicationContext, "Testing OnResume OK", Toast.LENGTH_SHORT).show()

    }




}