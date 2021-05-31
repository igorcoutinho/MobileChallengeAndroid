package br.com.igorcoutinho.mobilechallengeandroid.uiTest.adapters

import android.content.Context
import br.com.igorcoutinho.mobilechallengeandroid.data.GithubRepositoriesResponse
import br.com.igorcoutinho.mobilechallengeandroid.utils.Constants
import br.com.igorcoutinho.mobilechallengeandroid.viewmodels.GitHubViewModel
import br.com.igorcoutinho.mobilechallengeandroid.viewmodels.GithubLiveDataViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAdapter(
    context: Context,
    private val githubModelLiveDataModel: GithubLiveDataViewModel
) : Callback<GithubRepositoriesResponse> {

    private val databaseAdapter: GithubDatabaseAdapter = GithubDatabaseAdapter(context);
    private val githubDatabaseAdapter: GithubDatabaseAdapter = GithubDatabaseAdapter(context)

    var page = 0

    fun loadRepositoriesFromCloud(page: Int) {
        this.page = page
        try {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            val service = retrofit.create(GitHubViewModel::class.java)


            val callService: Call<GithubRepositoriesResponse> = service.listRepositories(page)
            callService.enqueue(this)

        } catch (ex: Exception) {
            throw ex
        }

    }

    override fun onResponse(
        call: Call<GithubRepositoriesResponse>,
        response: Response<GithubRepositoriesResponse>
    ) {
        if (response.isSuccessful) {
            val githubResponse = response.body()

            databaseAdapter.saveGithubRepositories(githubResponse!!.items)

            val repositories = githubDatabaseAdapter.getAllGithubRepositories(page)

            githubModelLiveDataModel.githubRepositoriesList.value = repositories

        } else {
            throw Exception("Erro ao consultar os repositórios: ${response.message()}")
        }

    }

    override fun onFailure(call: Call<GithubRepositoriesResponse>, t: Throwable) {
        throw Exception("Erro ao consultar os repositórios: ${t.message}")
    }


}