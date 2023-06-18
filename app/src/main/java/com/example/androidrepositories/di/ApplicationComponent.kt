package com.example.androidrepositories.di

import android.content.Context
import com.example.androidrepositories.di.module.LocalDataModule
import com.example.androidrepositories.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class,
        LocalDataModule::class]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}