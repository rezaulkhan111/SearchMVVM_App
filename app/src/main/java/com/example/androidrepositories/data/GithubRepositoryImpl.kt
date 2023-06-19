package com.example.androidrepositories.data

import com.example.androidrepositories.data.local.AppDbHelper
import com.example.androidrepositories.data.remote.GitHubApiService
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepositoryImpl @Inject constructor(
    private val apiService: GitHubApiService,
    private val dbHelper: AppDbHelper
) {
    fun fetchRepositories(
        searchWord: String,
        page: Int,
        perPage: Int
    ) = networkBoundResource(query = {
        dbHelper.getLDRepository()
    }, fetch = {
        delay(2000)
        apiService.getRepositories(searchWord, page, perPage)
    }, saveFetchResult = {
        dbHelper.insertRepository(it)
    })

    fun getOfflineRepositories() = dbHelper.getLDRepository()
}