package com.example.androidrepositories.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidrepositories.data.model.RepositoryDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryDao {
    @Insert
    suspend fun insertRepository(repositoryModel: RepositoryDetails)

    @Query("SELECT * FROM RepositoryDetails")
    fun getAllRepositories(): MutableList<RepositoryDetails>
}