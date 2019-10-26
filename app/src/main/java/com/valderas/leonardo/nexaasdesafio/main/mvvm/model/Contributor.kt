package com.valderas.leonardo.nexaasdesafio.main.mvvm.model

import android.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Contributor (
        @Expose
        var id: Long,
        @Expose
        var title: String,
        @Expose
        var body: String,
        @Expose
        var updated_at: String,
        @SerializedName("html_url")
        @Expose
        var url: String = "",
        @Expose
        var user: User = User()): BaseObservable()
