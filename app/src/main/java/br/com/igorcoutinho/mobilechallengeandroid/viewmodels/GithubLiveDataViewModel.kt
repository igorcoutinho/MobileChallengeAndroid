package br.com.igorcoutinho.mobilechallengeandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.igorcoutinho.mobilechallengeandroid.data.GithubRepository

class GithubLiveDataViewModel: ViewModel()  {
    val githubRepositoriesList : MutableLiveData<MutableList<GithubRepository>> by lazy {
        MutableLiveData<MutableList<GithubRepository>>()
    }

}