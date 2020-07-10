package br.com.nexaas.common.ui.base

sealed class ViewState<T> {
    class Success<T>(val data: T) : ViewState<T>()
    class Loading<T> : ViewState<T>()
    class Error<T>(val error: Throwable, val retry: (() -> Unit)? = null) : ViewState<T>()
}