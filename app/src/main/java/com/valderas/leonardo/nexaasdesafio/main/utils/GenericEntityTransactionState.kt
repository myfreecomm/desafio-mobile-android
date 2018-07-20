package com.valderas.leonardo.nexaasdesafio.main.utils

sealed class GenericEntityTransactionState {
    class IsLoading(val isloading: Boolean = false) : GenericEntityTransactionState()
    class IsSuccess<T>(val entity: T) : GenericEntityTransactionState()
    class Error(val msg: String?, val idResource: Int) : GenericEntityTransactionState()
}