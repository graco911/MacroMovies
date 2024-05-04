package com.gracodev.macromovies

import android.app.Application
import com.google.firebase.FirebaseApp
import com.gracodev.macromovies.modules.createAppModules
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
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

        AppCenter.start(
            this, "9f837bb2-7814-4701-8a90-6e61ec096724",
            Analytics::class.java, Crashes::class.java
        )
    }
}