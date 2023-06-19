package com.example.androidrepositories.data.local

import androidx.room.withTransaction
import com.example.androidrepositories.data.model.RepositoryResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class AppDbHelper @Inject constructor(private val dataBase: AppDataBase) {
    suspend fun insertRepository(repoRes: RepositoryResponse) {
        dataBase.apply {
            withTransaction {
                for (repoItem in repoRes.getItems()!!) {
                    val tempOwner = repoItem.getOwner()
                    repoItem.setOwnerId(tempOwner!!.getId())

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