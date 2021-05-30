package br.com.igorcoutinho.mobilechallengeandroid.data

import com.google.gson.annotations.SerializedName

data class GithubRepositoriesResponse (

    @SerializedName("total_count")
    val totalCount: Long,

    @SerializedName("items")
    val items: MutableList<GithubRepository>,
)