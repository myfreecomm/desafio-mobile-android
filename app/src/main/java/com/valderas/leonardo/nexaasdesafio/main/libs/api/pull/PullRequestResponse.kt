package com.valderas.leonardo.nexaasdesafio.main.libs.api.pull

import android.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Repo

data class PullRequestResponse(@Expose var repos: MutableList<Repo>) : BaseObservable()
