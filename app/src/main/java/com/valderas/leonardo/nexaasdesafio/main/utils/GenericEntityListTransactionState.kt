package com.valderas.leonardo.nexaasdesafio.main.utils

sealed class GenericEntityListTransactionState {
    class IsLoading(val isloading: Boolean = false) : GenericEntityListTransactionState()
    class IsSuccess<T>(val entityList: MutableList<T>) : GenericEntityListTransactionState()
    class Error(val msg: String?, val idResource: Int) : GenericEntityListTransactionState()
}