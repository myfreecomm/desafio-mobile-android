package com.valderas.leonardo.nexaasdesafio.main.repositry

import com.raizlabs.android.dbflow.kotlinextensions.save
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.valderas.leonardo.nexaasdesafio.main.libs.api.repo.RepoResponse
import com.valderas.leonardo.nexaasdesafio.main.libs.api.repo.RepoService
import com.valderas.leonardo.nexaasdesafio.main.utils.NetManager
import com.valderas.leonardo.nexaasdesafio.main.utils.Page
import io.reactivex.Single
import javax.inject.Inject
import com.valderas.leonardo.nexaasdesafio.R
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Repo

class MainRepository @Inject constructor(private val netManager: NetManager,
                                         private val service: RepoService) {

    fun getReposList(page: Page): Single<RepoResponse> {

        netManager.isConnectedToInternet?.let {
            when (it) {
                true -> return getReposFromService(page)
                false -> return getReposFromDataBases()
            }
        }

        return Single.error(Throwable())
    }

    private fun getReposFromService(page: Page): Single<RepoResponse> =
            service.getReposList(page.pageNumber, page.pageSize)

    private fun getReposFromDataBases(): Single<RepoResponse> {
        val listCars = SQLite.select().from(Repo::class.java).queryList()
        return Single.just(RepoResponse(listCars, false))
    }

    fun saveReposDataBases(repos: MutableList<Repo>) {
        repos.save()
    }
}