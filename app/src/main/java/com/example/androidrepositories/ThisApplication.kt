package com.example.androidrepositories

import android.app.Application
import com.example.androidrepositories.di.ApplicationComponent
import com.example.androidrepositories.di.DaggerApplicationComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThisApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): ApplicationComponent {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }
}