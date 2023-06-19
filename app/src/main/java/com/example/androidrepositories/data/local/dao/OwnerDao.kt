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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwners(owners: MutableList<OwnerDetails>)

    @Query("SELECT * FROM OwnerDetails")
    fun getAllOwners(): MutableList<OwnerDetails>

    @Query("SELECT * FROM OwnerDetails")
    fun getAllLDOwners(): Flow<MutableList<OwnerDetails>>

    @Query("DELETE FROM OwnerDetails")
    suspend fun deleteAllOwners()

    @Query("SELECT * FROM OwnerDetails WHERE id=:ownerId")
    fun getOwnerById(ownerId: Int): OwnerDetails
}