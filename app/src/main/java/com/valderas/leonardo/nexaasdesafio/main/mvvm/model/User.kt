package com.valderas.leonardo.nexaasdesafio.main.mvvm.model

import android.databinding.BaseObservable
import com.google.gson.annotations.Expose

data class User(@Expose var id: Long = 0, @Expose var login: String = "", @Expose var avatar_url: String = ""): BaseObservable()

