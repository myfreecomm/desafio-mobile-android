package com.example.desafioandroid.ui.shared

import androidx.lifecycle.MutableLiveData

class ViewState<D>(
    val status : Status = Status.NEUTRAL,
    val data : D? = null,
    val error : Throwable? = null
) {

    fun handleState(
        success : (D) -> Unit,
        error  : (Throwable) -> Unit,
        loading : () -> Unit){

        when (this.status){
            Status.SUCCESS -> {
                this.data?.let {
                    success(it)
                }
            }
            Status.LOADING -> {
                loading()
            }
            Status.ERROR -> {
                this.error?.let {
                    error(it)
                }
            }
            Status.NEUTRAL -> Unit
        }
    }

    fun <T> success(data: T? = null) : ViewState<T>{
        return ViewState(Status.SUCCESS,data,null)
    }

    fun <T> error(error : Throwable) : ViewState<T>{
        return ViewState(Status.ERROR,null,error)
    }


    enum class Status {
        LOADING, SUCCESS, ERROR, NEUTRAL
    }
}

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data : T?){
    this.postValue(ViewState(ViewState.Status.SUCCESS,data,null))
}

fun <T> MutableLiveData<ViewState<T>>.postError(error : Throwable){
    this.postValue(ViewState(ViewState.Status.ERROR,null,error))
}

fun <T> MutableLiveData<ViewState<T>>.postLoading(){
    this.postValue(ViewState(ViewState.Status.LOADING,null,null))
}