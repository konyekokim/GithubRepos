package com.chokus.konye.githubrepos.utilities

import com.chokus.konye.githubrepos.models.RepoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    //?q=android%20language:java&sort=stars&order=desc
    ///// This query fetches most trending android repository /////
    ///// For demo purpose we would call it directly
    //?page=2&per_page=100'
    //this query fetches most trending android repos... for demo purposes we are calling it directly

    @GET("search/repositories?q=android%20language:java&sort=stars&order=desc&per_page=10")
    fun getRepositories(@Query("page") page: Int): Call<RepoModel>
}