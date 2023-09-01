package com.example.androidrepositories.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidrepositories.data.local.dao.OwnerDao
import com.example.androidrepositories.data.local.dao.RepositoryDao
import com.example.androidrepositories.data.model.OwnerDetails
import com.example.androidrepositories.data.model.RepositoryDetails

@Database(
    entities = [
        RepositoryDetails::class,
        OwnerDetails::class
    ],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
    abstract fun ownerDao(): OwnerDao
}