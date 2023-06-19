package com.example.androidrepositories.ui.callback

import com.example.androidrepositories.data.model.RepositoryDetails

interface RepositoryCallback {
    fun repositoryItemClick(repositoryModel: RepositoryDetails, position: Int)
}