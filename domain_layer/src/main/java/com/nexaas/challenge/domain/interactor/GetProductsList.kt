package com.nexaas.challenge.domain.interactor

import com.nexaas.challenge.domain.core.Interactor
import com.nexaas.challenge.domain.model.ProductDomain
import io.reactivex.Observable
import io.reactivex.Single

class GetProductsList: Interactor<Observable<ArrayList<ProductDomain>>> {

    override fun execute(): Observable<ArrayList<ProductDomain>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}