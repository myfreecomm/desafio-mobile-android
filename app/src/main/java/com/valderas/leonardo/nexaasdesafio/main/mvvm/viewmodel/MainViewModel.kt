package com.valderas.leonardo.nexaasdesafio.main.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.valderas.leonardo.nexaasdesafio.R
import com.valderas.leonardo.nexaasdesafio.main.repositry.MainRepository
import com.valderas.leonardo.nexaasdesafio.main.utils.GenericEntityListTransactionState
import com.valderas.leonardo.nexaasdesafio.main.utils.GenericEntityTransactionState
import com.valderas.leonardo.nexaasdesafio.main.utils.Page
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val reposStateLive = MutableLiveData<GenericEntityTransactionState>()

    fun getReposList(page: Page) {
        mainRepository
                .getReposList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { reposStateLive.value = GenericEntityTransactionState.IsLoading(true) }
                .doFinally { reposStateLive.value = GenericEntityTransactionState.IsLoading(false) }
                .subscribeBy(
                        onSuccess = {
                            reposStateLive.value = GenericEntityTransactionState.IsSuccess(it.repos)
                            if (it.isService)
                                mainRepository.saveReposDataBases(it.repos)

                        },
                        onError = {
                            if (it.message!!.equals(R.string.connection_error))
                                reposStateLive.value = GenericEntityTransactionState.Error(null, R.string.connection_error)
                            else
                                reposStateLive.value = GenericEntityTransactionState.Error(null, R.string.generic_error)
                        }
                )
    }
}
