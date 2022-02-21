package com.abhiwisesa.moviecatalogue

import android.app.Application
import com.abhiwisesa.moviecatalogue.core.di.databaseModule
import com.abhiwisesa.moviecatalogue.core.di.networkModule
import com.abhiwisesa.moviecatalogue.core.di.repositoryModule
import com.abhiwisesa.moviecatalogue.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}