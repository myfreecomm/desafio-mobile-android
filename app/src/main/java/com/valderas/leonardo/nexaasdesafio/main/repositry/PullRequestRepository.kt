package com.valderas.leonardo.nexaasdesafio.main.repositry

import com.valderas.leonardo.nexaasdesafio.main.utils.NetManager
import com.valderas.leonardo.nexaasdesafio.main.utils.Page
import io.reactivex.Single
import javax.inject.Inject
import com.valderas.leonardo.nexaasdesafio.main.libs.api.pull.PullRequestService
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Contributor

class PullRequestRepository @Inject constructor(private val netManager: NetManager,
                                                private val service: PullRequestService) {

    fun getPullRequestList(owner: String, name: String, page: Page): Single<MutableList<Contributor>> {

        netManager.isConnectedToInternet?.let {
            when (it) {
                true -> return getPullsFromService(owner, name, page)
                false -> return getPullsFromDataBases(owner, name, page)
            }
        }

        return Single.error(Throwable())
    }

    private fun getPullsFromService(owner: String, name: String, page: Page): Single<MutableList<Contributor>> =
            service.getPullRequests(owner, name, page.pageNumber, page.pageSize)

    private fun getPullsFromDataBases(owner: String, name: String, page: Page): Single<MutableList<Contributor>> =
            Single.just(arrayListOf())

}