package com.niknightarts.githubclient.ui.repositories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niknightarts.githubclient.R
import com.niknightarts.githubclient.data.network.objects.repo_search.RepositoryItem
import com.squareup.picasso.Picasso

class RepositoriesAdapter(
    private val callback: OnRepositoryItemClickCallback
) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {
    private var models = ArrayList<RepositoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = models[position]
        Picasso.get().load(model.owner.avatar_url)
            .placeholder(R.drawable.ic_person_24)
            .error(R.drawable.ic_person_24)
            .fit()
            .centerInside()
            .into(holder.image)
        holder.title.text = model.full_name
        holder.info.text = model.description
        holder.stars.text = "* ${model.stargazers_count}"

    }

    override fun getItemCount(): Int = models.size

    fun setRepositoryItems(list: List<RepositoryItem>?) {
        models.clear()
        list?.let {
            models.addAll(list)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView by lazy { itemView.findViewById<ImageView>(R.id.item_repository_image) }
        val info: TextView by lazy { itemView.findViewById<TextView>(R.id.item_repository_info) }
        val stars: TextView by lazy { itemView.findViewById<TextView>(R.id.item_repository_stars) }
        val title: TextView by lazy { itemView.findViewById<TextView>(R.id.item_repository_title) }
    }

    interface OnRepositoryItemClickCallback {
        fun onRepositoryItemClick(id: String)
    }
}
