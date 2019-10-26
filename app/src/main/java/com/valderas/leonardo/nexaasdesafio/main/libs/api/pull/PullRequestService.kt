package com.valderas.leonardo.nexaasdesafio.main.libs.api.pull

import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Contributor
import io.reactivex.Single
import retrofit2.http.*

interface PullRequestService {

    @Headers("User-Agent: NexaasDesafio")
    @GET("repos/{owner}/{repo}/pulls")
    fun getPullRequests(@Path("owner")owner: String,
                           @Path("repo")repo: String,
                           @Query(value = "page", encoded = true) page: Int,
                           @Query(value = "per_page", encoded = true) per_page: Int): Single<MutableList<Contributor>>
}