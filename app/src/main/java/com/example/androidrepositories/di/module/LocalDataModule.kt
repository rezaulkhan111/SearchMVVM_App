package com.example.androidrepositories.di.module

import android.content.Context
import androidx.room.Room
import com.example.androidrepositories.data.local.AppDataBase
import com.example.androidrepositories.data.local.AppDbHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataModule {

    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "githubProject.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideAppDbHelper(appDatabase: AppDataBase) = AppDbHelper(appDatabase)
}