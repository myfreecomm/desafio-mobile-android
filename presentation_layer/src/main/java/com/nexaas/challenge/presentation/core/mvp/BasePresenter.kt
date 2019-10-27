package com.nexaas.challenge.presentation.core.mvp

import io.reactivex.disposables.Disposable

internal abstract class BasePresenter<T : BaseView> {

    internal var disposable: Disposable? = null
    internal var view: T? = null

    open fun attachView(view: T) {
        this.view = view
    }

    open fun detachView() {
        this.view = null
        cancelDisposable()
    }

    fun onDestroyView() {
        cancelDisposable()
        detachView()
    }

    private fun cancelDisposable() {
        disposable?.dispose()
        disposable = null
    }

}