package com.chokus.konye.githubrepos.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.chokus.konye.githubrepos.R
import com.chokus.konye.githubrepos.adapter.RepoRecyclerListAdapter
import com.chokus.konye.githubrepos.models.Item
import com.chokus.konye.githubrepos.models.RepoModel
import com.chokus.konye.githubrepos.utilities.APIService
import com.chokus.konye.githubrepos.utilities.GithubAPIUtils
import kotlinx.android.synthetic.main.activity_repos_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposListActivity : AppCompatActivity() {
    var reposList : MutableList<Item> = arrayListOf()
    var apiService : APIService? = null
    lateinit var recyclerAdapter : RepoRecyclerListAdapter
    private var layoutManager : LinearLayoutManager? = null
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos_list)
        setTitle(R.string.repo_list_activity_name)
        apiService = GithubAPIUtils.apiService
        getRepos()
        viewActions()
    }

    private fun viewActions(){
        // set up RecyclerView
        layoutManager = LinearLayoutManager(this)
        recyclerAdapter = RepoRecyclerListAdapter(reposList)
        repo_recyclerView.layoutManager = layoutManager
        repo_recyclerView.adapter = recyclerAdapter
        setRecyclerListenerForPagination()
    }

    private fun getRepos(){
        //makes a call to get trending repositories
        apiService?.getRepositories(page)?.enqueue(object : Callback<RepoModel>{
            override fun onFailure(call: Call<RepoModel>?, t: Throwable?) {
                // take action to show that repositories were not gotten
                toastMethod("Failed to get trending repos")
            }
            override fun onResponse(call: Call<RepoModel>?, response: Response<RepoModel>?) {
                if(response != null){
                    if(response.isSuccessful){
                        val repo = response.body()
                        reposList.addAll(repo?.items!!)
                        recyclerAdapter.repos = reposList
                        recyclerAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    private val lastItemShowingPosition : Int
        get() = layoutManager!!.findLastVisibleItemPosition()

    private fun setRecyclerListenerForPagination(){
        //setting up the recycler listener for endless Pagination
        repo_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalNumberOfItems = recyclerView!!.layoutManager.itemCount
                if (totalNumberOfItems == lastItemShowingPosition + 1) {
                    //last item is now showing so increment page count and load more of the repos
                    page += 1
                    getRepos()
                }
            }
        })
    }

    private fun toastMethod(message : String?){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
