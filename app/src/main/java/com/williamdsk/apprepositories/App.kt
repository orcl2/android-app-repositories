package com.williamdsk.apprepositories

import android.app.Application
import com.williamdsk.apprepositories.data.di.DataModule
import com.williamdsk.apprepositories.domain.di.DomainModule
import com.williamdsk.apprepositories.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}