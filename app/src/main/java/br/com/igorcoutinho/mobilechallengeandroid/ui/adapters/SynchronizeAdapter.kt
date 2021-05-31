package br.com.igorcoutinho.mobilechallengeandroid.ui.adapters

import android.content.Context
import br.com.igorcoutinho.mobilechallengeandroid.viewmodels.GithubLiveDataViewModel

class SynchronizeAdapter(
    context: Context,
    githubModelLiveDataModel: GithubLiveDataViewModel
) {

    private val retrofitAdapter: RetrofitAdapter = RetrofitAdapter(context, githubModelLiveDataModel)


    fun syncData(page: Int) {
        try {
            retrofitAdapter.loadRepositoriesFromCloud(page)
        }catch (ex: Exception) {
            throw ex;
        }
    }

}