package br.com.igorcoutinho.mobilechallengeandroid.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.igorcoutinho.mobilechallengeandroid.R
import br.com.igorcoutinho.mobilechallengeandroid.ui.adapters.GithubDatabaseRepository
import br.com.igorcoutinho.mobilechallengeandroid.ui.adapters.GithubRepositoryAdapter


class MainActivity() : AppCompatActivity() {

    private var recyclerViewListGithubRepositories: RecyclerView? = null

    private val githubDatabaseRepository: GithubDatabaseRepository = GithubDatabaseRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            recyclerViewListGithubRepositories =
                findViewById(R.id.recycler_view_list_github_repositories)

            loadList()

        }catch (ex: Exception) {
            Toast.makeText(this, "Erro ao carregar a activity: ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadList() {
        try {
            val repositoriesList = githubDatabaseRepository.getAllGithubRepositories()
            val mAdapter = GithubRepositoryAdapter(repositoriesList)

            recyclerViewListGithubRepositories!!.adapter = mAdapter
        }catch (ex: java.lang.Exception) {
            Toast.makeText(this, "Erro ao carregar a lista: ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }


}