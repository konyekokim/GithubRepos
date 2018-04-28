package com.chokus.konye.githubrepos.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.chokus.konye.githubrepos.R
import com.chokus.konye.githubrepos.models.Item
import com.chokus.konye.githubrepos.utilities.StoreItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var repos : Item? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        repos = StoreItem.viewedRepo
        if(repos != null){
            title = repos?.name
            viewActions()
        }
    }

    private fun viewActions(){
        Picasso.get().load(repos?.owner?.avatarUrl).into(user_display_imageView)
        repo_title.text = repos?.name
        repo_full_description.text = repos?.description
        repo_stars.text = repos?.stargazersCount.toString()
        repo_watchers.text = repos?.watchers.toString()
        repo_forks.text = repos?.forksCount.toString()
        repo_language.text = repos?.language
        repo_open_issues.text = repos?.openIssues.toString()
    }


}
