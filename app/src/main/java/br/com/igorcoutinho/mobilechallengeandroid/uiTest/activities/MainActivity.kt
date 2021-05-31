package br.com.igorcoutinho.mobilechallengeandroid.uiTest.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.com.igorcoutinho.mobilechallengeandroid.R
import br.com.igorcoutinho.mobilechallengeandroid.data.GithubRepository
import br.com.igorcoutinho.mobilechallengeandroid.uiTest.adapters.GithubRepositoryAdapter
import br.com.igorcoutinho.mobilechallengeandroid.uiTest.adapters.SynchronizeAdapter
import br.com.igorcoutinho.mobilechallengeandroid.utils.Constants
import br.com.igorcoutinho.mobilechallengeandroid.viewmodels.GithubLiveDataViewModel
import com.couchbase.lite.Database
import com.couchbase.lite.DatabaseConfiguration


class MainActivity() : AppCompatActivity() {

    private var recyclerViewListGithubRepositories: RecyclerView? = null

    private val githubModelLiveDataModel: GithubLiveDataViewModel by viewModels()

    private val repositoriesList = mutableListOf<GithubRepository>()
    private val mAdapter = GithubRepositoryAdapter(repositoriesList)

    private var page = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val config = DatabaseConfiguration(this)
        val db = Database(Constants.DATABASE_NAME, config)
        db.delete()

        try {
            recyclerViewListGithubRepositories =
                findViewById(R.id.recycler_view_list_github_repositories)
            val buttonLoadMore: Button = findViewById(R.id.button_sync)
            val textViewTotalCount: TextView = findViewById(R.id.text_view_total_count)

            val synchronizeAdapter = SynchronizeAdapter(this, githubModelLiveDataModel)

            buttonLoadMore.setOnClickListener {
                synchronizeAdapter.syncData(page)
                page++
            }


            val githubRepositoriesListObserver = Observer<MutableList<GithubRepository>> { newList ->
                repositoriesList.addAll(newList)
                mAdapter.notifyDataSetChanged()
                textViewTotalCount.text = "Total: ${repositoriesList.size}"
            }

            githubModelLiveDataModel.githubRepositoriesList.observe(this, githubRepositoriesListObserver)

            recyclerViewListGithubRepositories!!.adapter = mAdapter





        }catch (ex: Exception) {
            Toast.makeText(this, "Erro ao carregar a activity: ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }

}