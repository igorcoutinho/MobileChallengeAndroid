package br.com.igorcoutinho.mobilechallengeandroid.uiTest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.igorcoutinho.mobilechallengeandroid.R
import br.com.igorcoutinho.mobilechallengeandroid.data.GithubRepository

class GithubRepositoryAdapter(private val dataset: MutableList<GithubRepository>) :
    RecyclerView.Adapter<GithubRepositoryAdapter.ViewHolder>(){

        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val textViewGithubRepositoryName: TextView = view.findViewById(R.id.text_view_github_repository_name)
            val textViewGithubRepositoryDescription: TextView = view.findViewById(R.id.text_view_github_repository_description)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_github_repositories, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewGithubRepositoryName.text = dataset[position].name
        holder.textViewGithubRepositoryDescription.text = dataset[position].description
    }

    override fun getItemCount() = dataset.size


}
