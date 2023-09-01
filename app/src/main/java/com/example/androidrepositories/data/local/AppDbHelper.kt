package com.example.androidrepositories.data.local

import android.util.Log
import androidx.room.withTransaction
import com.example.androidrepositories.data.model.RepositoryResponse
import com.example.androidrepositories.utils.AppConstant
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class AppDbHelper @Inject constructor(private val dataBase: AppDataBase) {
    suspend fun insertRepository(repoRes: RepositoryResponse) {
        dataBase.apply {
            withTransaction {
                for (repoItem in repoRes.getItems()!!) {
                    val tempOwner = repoItem.getOwner()
                    repoItem.setOwnerId(tempOwner!!.getId())

                    if (repoItem.getStargazersCount()!! / 50 <= 100) {
                        Log.e("ADbH", "insertRepository: if")
                        repoItem.setViewType(AppConstant.VIEW_TYPE)
                    } else if (repoItem.getStargazersCount()!! % 2 == 0) {
                        repoItem.setViewType(AppConstant.VIEW_TYPE1)
                        Log.e("ADbH", "insertRepository: else if")
                    } else {
                        repoItem.setViewType(AppConstant.VIEW_TYPE2)
                        Log.e("ADbH", "insertRepository: else")
                    }

                    repositoryDao().insertRepository(repoItem)
                    ownerDao().insertOwner(repoItem.getOwner()!!)
                }
            }
        }
    }

    fun getLDRepository() = flow {
        emit(RepositoryResponse().apply {
            dataBase.apply {
                withTransaction {
                    val reposTempList = repositoryDao().getAllRepositories()
                    for (reposItem in reposTempList) {
                        reposItem.setOwner(ownerDao().getOwnerById(reposItem.getOwnerId()!!))
                    }
                    setItems(reposTempList)
                }
            }
        })
    }
}