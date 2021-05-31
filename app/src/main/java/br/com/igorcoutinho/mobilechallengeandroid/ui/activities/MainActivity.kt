package br.com.igorcoutinho.mobilechallengeandroid.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.com.igorcoutinho.mobilechallengeandroid.R
import br.com.igorcoutinho.mobilechallengeandroid.data.GithubRepository
import br.com.igorcoutinho.mobilechallengeandroid.ui.adapters.GithubDatabaseRepository
import br.com.igorcoutinho.mobilechallengeandroid.ui.adapters.GithubRepositoryAdapter
import br.com.igorcoutinho.mobilechallengeandroid.viewmodels.GithubLiveDataViewModel
import java.util.*


class MainActivity() : AppCompatActivity() {

    private var recyclerViewListGithubRepositories: RecyclerView? = null

    private val githubDatabaseRepository: GithubDatabaseRepository = GithubDatabaseRepository(this)

    private val githubModelLiveDataModel: GithubLiveDataViewModel by viewModels()

    private val repositoriesList = mutableListOf<GithubRepository>()

    private var page: Int = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            recyclerViewListGithubRepositories =
                findViewById(R.id.recycler_view_list_github_repositories)

            val buttonLoadMore: Button = findViewById(R.id.button_load_more)

            buttonLoadMore.setOnClickListener {
                val obj = GithubRepository(
                    id = 1,
                    name = "Name $page",
                    description = "",
                    stargazersCount = 10,
                    forksCount = 12,
                    githubRepositoryOwner = null
                )

                page++

                githubModelLiveDataModel.githubRepositoriesList.value = mutableListOf(obj)
            }


            val githubRepositoriesListObserver = Observer<MutableList<GithubRepository>> { newList ->
                repositoriesList.addAll(newList)
                //loadList()
            }

            githubModelLiveDataModel.githubRepositoriesList.observe(this, githubRepositoriesListObserver)

            loadList()

        }catch (ex: Exception) {
            Toast.makeText(this, "Erro ao carregar a activity: ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadList() {
        try {
            //val repositoriesList = githubDatabaseRepository.getAllGithubRepositories()
            val mAdapter = GithubRepositoryAdapter(repositoriesList)
            recyclerViewListGithubRepositories!!.adapter = mAdapter
        }catch (ex: java.lang.Exception) {
            Toast.makeText(this, "Erro ao carregar a lista: ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }


}