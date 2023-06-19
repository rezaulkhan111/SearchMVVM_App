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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositoryModels: MutableList<RepositoryDetails>)

    @Query("SELECT * FROM RepositoryDetails")
    fun getAllLDRepositories(): Flow<MutableList<RepositoryDetails>>

    @Query("SELECT * FROM RepositoryDetails")
    fun getAllRepositories(): MutableList<RepositoryDetails>

    @Query("DELETE FROM RepositoryDetails")
    suspend fun deleteAllRepositories()

    @Query("SELECT * FROM RepositoryDetails WHERE id=:repositoryId")
    fun getAllLDRepositoriesByCategory(repositoryId: Int): Flow<MutableList<RepositoryDetails>>

    @Query("SELECT * FROM RepositoryDetails WHERE id=:repositoryId")
    fun getLDRepositoryById(repositoryId: Int): Flow<RepositoryDetails>
}