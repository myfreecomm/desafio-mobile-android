package com.example.challengeaccepted.platform.base

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import java.lang.ref.WeakReference

abstract class BaseViewModel<N> : ViewModel(), KoinComponent {

    private lateinit var navigator: WeakReference<N>

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator(): N = navigator.get()!!
}