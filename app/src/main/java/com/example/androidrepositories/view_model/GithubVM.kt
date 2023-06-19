package com.example.androidrepositories.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androidrepositories.data.GithubRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class GithubVM @Inject constructor(private val gitRepoImpl: GithubRepositoryImpl) : ViewModel() {
    fun fetchRepositories(
        searchWord: String,
        page: Int,
        perPage: Int
    ) = gitRepoImpl.fetchRepositories(
        searchWord,
        page,
        perPage
    ).asLiveData()

    fun getOfflineRepositories() = gitRepoImpl.getOfflineRepositories().asLiveData()
}