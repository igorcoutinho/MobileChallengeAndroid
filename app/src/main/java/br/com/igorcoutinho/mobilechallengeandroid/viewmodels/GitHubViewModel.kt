package br.com.igorcoutinho.mobilechallengeandroid.viewmodels

import br.com.igorcoutinho.mobilechallengeandroid.data.GithubRepositoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GitHubViewModel {
    @GET("search/repositories?q=language:Java&sort=stars")
    fun listRepositories(@Query("page") page: Int): Call<GithubRepositoriesResponse>
}