package com.valderas.leonardo.nexaasdesafio.main.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.valderas.leonardo.nexaasdesafio.R
import com.valderas.leonardo.nexaasdesafio.main.repositry.MainRepository
import com.valderas.leonardo.nexaasdesafio.main.repositry.PullRequestRepository
import com.valderas.leonardo.nexaasdesafio.main.utils.GenericEntityListTransactionState
import com.valderas.leonardo.nexaasdesafio.main.utils.GenericEntityTransactionState
import com.valderas.leonardo.nexaasdesafio.main.utils.Page
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PullRequestViewModel @Inject constructor(private val pullRepository: PullRequestRepository) : ViewModel() {

    val pullStateLive = MutableLiveData<GenericEntityTransactionState>()

    fun getPullRequestList(owner: String, name: String, page: Page) {
        pullRepository
                .getPullRequestList(owner, name, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { pullStateLive.value = GenericEntityTransactionState.IsLoading(true) }
                .doFinally { pullStateLive.value = GenericEntityTransactionState.IsLoading(false) }
                .subscribeBy(
                        onSuccess = {
                            pullStateLive.value = GenericEntityTransactionState.IsSuccess(it) },
                        onError = {
                            if (it.message!!.equals(R.string.connection_error))
                                pullStateLive.value = GenericEntityTransactionState.Error(null, R.string.connection_error)
                            else
                                pullStateLive.value = GenericEntityTransactionState.Error(null, R.string.generic_error)
                        }
                )
    }
}
