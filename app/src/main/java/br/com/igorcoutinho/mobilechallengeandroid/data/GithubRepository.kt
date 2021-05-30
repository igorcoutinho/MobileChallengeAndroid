package br.com.igorcoutinho.mobilechallengeandroid.data

import com.google.gson.annotations.SerializedName

data class GithubRepository (
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("owner")
    val githubRepositoryOwner: GithubRepositoryOwner?,

    @SerializedName("stargazers_count")
    val stargazersCount: Long,

    @SerializedName("forks_count")
    val forksCount: Long,
)