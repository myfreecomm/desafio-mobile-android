package com.welbertsoft.androidteste.ui.fragments.cart

import com.welbertsoft.androidteste.repository.response.DataResponseElement
import java.util.ArrayList

/**
Created By Welbert Moreira on 24/06/2020 : 21:23
 */
interface CartFragmentView {
    fun onItensReceived(result: ArrayList<DataResponseElement>)
    fun onConnectionFailed()

    fun showProgress()
    fun hideProgress()
}