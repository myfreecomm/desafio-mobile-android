package com.example.challengeaccepted.platform.network

import android.annotation.SuppressLint
import com.example.challengeaccepted.platform.util.GsonHelper
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class Network() {
    fun iOScheduler(): Scheduler = Schedulers.io()
    fun mainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    val gson = GsonHelper().gson

    @SuppressLint("CheckResult")
    inline fun <reified T : Any> request(
            interactor: Observable<T?>?,
            response: Response<T?>
    ) {
        interactor
                ?.observeOn(mainScheduler())
                ?.subscribeOn(iOScheduler())
                ?.doOnSubscribe { response.onSubscribe() }
                ?.doOnTerminate { response.onTerminate() }
                ?.subscribe(
                        {
                            if (it != null) {
                                val jsonString = gson.toJson(it)
                                response.subscribe(gson.fromJson(jsonString, T::class.java))

                            } else {
                                error("null")
                            }
                        },
                        {
                            try {
                                val json = (it as retrofit2.HttpException).response()?.errorBody()?.string()
                                val resError = gson.fromJson(json, ResponseError::class.java)
                                response.error(Throwable(resError.message))
                            } catch (e: Exception) {
                                response.error(e)
                            }
                        })
    }

}
