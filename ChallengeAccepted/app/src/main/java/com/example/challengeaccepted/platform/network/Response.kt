package com.example.challengeaccepted.platform.network

data class Response<T>(var subscribe: (data: T) -> Unit,
                       var onSubscribe: () -> Unit,
                       var onTerminate: () -> Unit,
                       var error: (error: Throwable) -> Unit)

