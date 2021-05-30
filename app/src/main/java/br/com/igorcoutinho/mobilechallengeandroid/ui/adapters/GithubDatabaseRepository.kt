package br.com.igorcoutinho.mobilechallengeandroid.ui.adapters

import android.content.Context
import android.widget.Toast
import br.com.igorcoutinho.mobilechallengeandroid.data.GithubRepository
import br.com.igorcoutinho.mobilechallengeandroid.utils.GlobalUtil
import com.couchbase.lite.*


class GithubDatabaseRepository(private val context: Context) {
    fun saveGithubRepositories(githubRepositoryList:  MutableList<GithubRepository>) {
        try {
            val config = DatabaseConfiguration(context)
            val database = Database(GlobalUtil.DATABASE_NAME, config)

            database.inBatch {
                for (item in githubRepositoryList) {
                    val doc = MutableDocument()
                    doc.setValue("id", item.id)
                    doc.setValue("name", item.name)
                    doc.setValue("description", item.description)
                    doc.setValue("stargazersCount", item.stargazersCount)
                    doc.setValue("forksCount", item.forksCount)

                    try {
                        database.save(doc)
                    } catch (e: CouchbaseLiteException) {
                        //Log.e(TAG, e.toString())
                        throw Exception("Erro ao salvar os dados no banco: ${e.message}")
                    }
                }

            }
        }catch (ex: Exception) {
            throw  ex
        }
    }

    fun getAllGithubRepositories(page: Int = 1): MutableList<GithubRepository> {
        try {
            val config = DatabaseConfiguration(context)
            val db = Database(GlobalUtil.DATABASE_NAME, config)

            val query: Query = QueryBuilder.select(SelectResult.all())
                .from(DataSource.database(db))
            val result = query.execute()

            val listResult = mutableListOf<GithubRepository>()

            for (item in result) {
                val all: Dictionary = item.getDictionary(GlobalUtil.DATABASE_NAME)

                listResult.add(
                    GithubRepository(
                        id = all.getLong("id"),
                        name = all.getString("name"),
                        description = all.getString("description"),
                        stargazersCount = all.getLong("stargazersCount"),
                        forksCount = all.getLong("forksCount"),
                        githubRepositoryOwner = null
                    )
                )
            }

            return listResult

        }catch (ex: java.lang.Exception) {
            throw ex
        }
    }
}