package com.example.challengeaccepted.platform.data.network

import com.example.challengeaccepted.platform.data.network.exception.ErrorException

sealed class State {
    object Success : State()
    class Error(val exception: ErrorException? = null) : State()
    class Loading(val isLoading: Boolean) : State()
    object CallingNetwork : State()
    object WritingDb : State()
}

data class Resource<out T>(val state: State, val data: T? = null, val message: Int = -1) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                State.Success,
                data,
                -1
            )
        }

        fun <T> error(exception: ErrorException? = null): Resource<T> {
            return Resource(
                State.Error(exception)
            )
        }

        fun <T> loading(isLoading: Boolean = false): Resource<T> {
            return Resource(
                State.Loading(isLoading)
            )
        }

        fun <T> callingNetwork(): Resource<T> {
            return Resource(
                State.CallingNetwork
            )
        }

        fun <T> writingDb(data: T?): Resource<T> {
            return Resource(
                State.WritingDb,
                data
            )
        }
    }
}