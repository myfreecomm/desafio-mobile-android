package com.valderas.leonardo.nexaasdesafio.main.libs.di.api

import com.valderas.leonardo.nexaasdesafio.main.libs.api.pull.PullRequestService
import com.valderas.leonardo.nexaasdesafio.main.libs.api.repo.RepoService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
object RepoServiceModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideRepoService(retrofit: Retrofit): RepoService = retrofit.create(RepoService::class.java)

    @JvmStatic
    @Provides
    @Singleton
    fun providePullService(retrofitPull: Retrofit): PullRequestService = retrofitPull.create(PullRequestService::class.java)
}