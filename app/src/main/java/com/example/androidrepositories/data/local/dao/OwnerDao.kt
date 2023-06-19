package com.example.androidrepositories.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidrepositories.data.model.OwnerDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface OwnerDao {
    @Insert
    suspend fun insertOwner(owner: OwnerDetails)

    @Query("SELECT * FROM OwnerDetails WHERE id=:ownerId")
    fun getOwnerById(ownerId: Int): OwnerDetails
}