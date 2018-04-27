package com.chokus.konye.githubrepos.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RepoModel {
    @SerializedName("total_count")
    @Expose
    var totalCount : Int = 0

    @SerializedName("incomplete_results")
    @Expose
    var isinCompleteResults : Boolean = false

    @SerializedName("items")
    @Expose
    var items : List<Item>? = null
}