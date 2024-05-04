package com.gracodev.macromovies

import android.app.Application
import com.google.firebase.FirebaseApp
import com.gracodev.macromovies.modules.createAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        val appModules = createAppModules()

        startKoin {
            androidContext(applicationContext)
            modules(appModules)
        }
    }
}