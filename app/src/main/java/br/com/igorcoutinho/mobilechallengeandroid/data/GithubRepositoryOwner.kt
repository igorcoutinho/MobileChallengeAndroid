package br.com.igorcoutinho.mobilechallengeandroid.data

import com.google.gson.annotations.SerializedName

data class GithubRepositoryOwner (
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
)