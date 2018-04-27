package com.chokus.konye.githubrepos.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chokus.konye.githubrepos.R
import com.chokus.konye.githubrepos.activities.DetailActivity
import com.chokus.konye.githubrepos.models.Item
import com.chokus.konye.githubrepos.utilities.StoreItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repos_list_item.view.*

class RepoRecyclerListAdapter(repos : MutableList<Item>) : RecyclerView.Adapter<RepoRecyclerListAdapter.ViewHolder>(){
    var repos : MutableList<Item> = repos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoRecyclerListAdapter.ViewHolder{
        val inflatedLayout = LayoutInflater.from(parent.context).inflate(R.layout.repos_list_item, parent, false)
        return ViewHolder(inflatedLayout)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: RepoRecyclerListAdapter.ViewHolder, position: Int) {
        val repo = repos[position]
        holder.bindRepos(repo)
    }

    class ViewHolder(v : View) : RecyclerView.ViewHolder(v), View.OnClickListener{
        private var view : View = v
        private var repo : Item? = null
        init{
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            StoreItem.viewedRepo = repo
            context.startActivity(intent)
        }

        fun bindRepos(repo : Item){
            this.repo = repo
            Picasso.get().load(repo.owner?.avatarUrl)
                    .placeholder(R.color.backgroundColor)
                    .error(android.R.drawable.stat_notify_error)
                    .centerInside()
                    .tag(itemView.context)
                    .into(view.user_dp)
            
            view.repo_title_textView.text = repo.name
            view.repo_description_textView.text = repo.description
            view.repo_watchers_textView.text = repo.forksCount.toString()
            view.repo_language.text = repo.language
        }
    }

}