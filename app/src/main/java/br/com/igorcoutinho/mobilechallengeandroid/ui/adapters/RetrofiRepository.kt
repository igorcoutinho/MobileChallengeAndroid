package br.com.igorcoutinho.mobilechallengeandroid.ui.adapters

import br.com.igorcoutinho.mobilechallengeandroid.data.GithubRepositoriesResponse
import br.com.igorcoutinho.mobilechallengeandroid.viewmodels.GitHubViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofiRepository (private val databaseRepository: GithubDatabaseRepository ): Callback<GithubRepositoriesResponse> {

    fun getRepositoriesFromCloud() {
        try {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            val service = retrofit.create(GitHubViewModel::class.java)


            val callService: Call<GithubRepositoriesResponse> = service.listRepositories(2)
            callService.enqueue(this)

        }catch (ex: Exception) {
            throw ex
        }

    }

    override fun onResponse(
        call: Call<GithubRepositoriesResponse>,
        response: Response<GithubRepositoriesResponse>
    ) {
        if(response.isSuccessful) {
            val githubResponse = response.body()

            databaseRepository.saveGithubRepositories(githubResponse!!.items)

        }else {
            throw Exception("Erro ao consultar os repositórios: ${response.message()}")
        }

    }

    override fun onFailure(call: Call<GithubRepositoriesResponse>, t: Throwable) {
        throw Exception("Erro ao consultar os repositórios: ${t.message}")
    }


}