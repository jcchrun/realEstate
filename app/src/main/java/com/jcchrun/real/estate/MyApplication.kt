package com.jcchrun.real.estate

import android.app.Application
import com.facebook.stetho.Stetho
import com.jcchrun.real.commons.app.di.commonsAppModule
import com.jcchrun.real.domain.di.domainModule
import com.jcchrun.real.estate.di.appModule
import com.jcchrun.real.locale.di.localeModule
import com.jcchrun.real.presentation.di.presentationModule
import com.jcchrun.real.remote.di.remoteModule
import com.jcchrun.real.repositories.di.repositoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupDi()
        setupStetho()
    }

    private fun setupDi() {
        startKoin {

            androidContext(this@MyApplication)

            modules(
                listOf(
                    appModule,
                    commonsAppModule,
                    presentationModule,
                    domainModule,
                    repositoriesModule,
                    remoteModule,
                    localeModule
                )
            )

        }
    }

    private fun setupStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}