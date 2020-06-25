package com.welbertsoft.androidteste.ui.fragments.cart

import com.welbertsoft.androidteste.repository.RetrofitBase
import com.welbertsoft.androidteste.repository.response.DataResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
Created By Welbert Moreira on 24/06/2020 : 21:23
 */
class CartFragmentPresenter(val cartFragmentView: CartFragmentView) {


    fun loadItens() {
        cartFragmentView.showProgress()
        RetrofitBase.getDataService().getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<DataResponse> {
                override fun onSuccess(result: DataResponse) {
                    cartFragmentView.onItensReceived(result)
                    cartFragmentView.hideProgress()
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    cartFragmentView.hideProgress()
                    cartFragmentView.onConnectionFailed()
                }

            })
    }

}