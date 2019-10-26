package com.valderas.leonardo.nexaasdesafio.main.libs.api.repo

import android.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Repo

data class RepoResponse(@SerializedName("items")
                        @Expose var repos: MutableList<Repo>,
                        var isService: Boolean = true) : BaseObservable()
