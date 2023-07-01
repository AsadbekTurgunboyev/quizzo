package com.example.quizzo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.quizzo.di.NetworkModule
import com.example.quizzo.di.repositoryModule
import com.example.quizzo.di.useCaseModule
import com.example.quizzo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(NetworkModule))
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}