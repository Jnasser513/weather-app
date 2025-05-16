package com.jnasser.weatherapp

import android.app.Application
import android.content.Context
import com.jnasser.core.data.di.coreDataModule
import com.jnasser.weather.network.datasources.di.weatherNetworkModule
import com.jnasser.weather.presentation.di.weatherPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class WeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@WeatherApp)
            modules(
                weatherPresentationModule,
                coreDataModule,
                weatherNetworkModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }
}