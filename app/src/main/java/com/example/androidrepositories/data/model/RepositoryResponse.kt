package com.example.androidrepositories.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RepositoryResponse {
    @SerializedName("total_count")
    @Expose
    private var totalCount: Int? = null

    @SerializedName("incomplete_results")
    @Expose
    private var incompleteResults: Boolean? = null

    @SerializedName("items")
    @Expose
    private var items: MutableList<RepositoryDetails>? = null

    fun getTotalCount(): Int? {
        return totalCount
    }

    fun setTotalCount(totalCount: Int) {
        this.totalCount = totalCount
    }

    fun getIncompleteResults(): Boolean? {
        return incompleteResults
    }

    fun setIncompleteResults(incompleteResults: Boolean) {
        this.incompleteResults = incompleteResults
    }

    fun getItems(): MutableList<RepositoryDetails>? {
        return items
    }

    fun setItems(items: MutableList<RepositoryDetails>) {
        this.items = items
    }
}