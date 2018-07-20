package com.valderas.leonardo.nexaasdesafio.main.libs.api.repo

import io.reactivex.Single
import retrofit2.http.*

interface RepoService {

    @GET("search/repositories?q=language:Java&sort=stars")
    fun getReposList(@Query(value = "page", encoded = true) page: Int,
                          @Query(value = "per_page", encoded = true) per_page: Int): Single<RepoResponse>
}