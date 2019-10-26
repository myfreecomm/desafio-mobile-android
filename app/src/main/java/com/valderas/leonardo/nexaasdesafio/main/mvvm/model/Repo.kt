package com.valderas.leonardo.nexaasdesafio.main.mvvm.model

import android.databinding.BaseObservable
//import com.google.auto.value.AutoValue
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.valderas.leonardo.nexaasdesafio.main.libs.db.RepoDataBases

@Table(database = RepoDataBases::class)
data class Repo(
                @PrimaryKey
                @Expose var id: Long = 0,
                @Column
                @Expose var name: String = "",
                @Column
                @Expose var description: String = "",
                @Expose var owner: User = User(),
                @Column
                @SerializedName("stargazers_count")
                @Expose var stargazersCount: Long = 0,
                @Column
                @SerializedName("forks_count")
                @Expose var forksCount: Long = 0,
                @Column
                @SerializedName("contributors_url")
                @Expose var contributorsUrl: String = "",
                @Column
                @SerializedName("created_at")
                @Expose var createdDate: String = "",
                @Column
                @SerializedName("updated_at")
                @Expose var updatedDate: String = "") : BaseObservable()