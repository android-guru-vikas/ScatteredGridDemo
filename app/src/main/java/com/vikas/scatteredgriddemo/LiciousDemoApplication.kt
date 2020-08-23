package com.vikas.scatteredgriddemo

import android.app.Application
import timber.log.Timber

class LiciousDemoApplication : Application() {

    companion object {
        private var instance: LiciousDemoApplication? = null

        @Synchronized
        fun getInstance(): LiciousDemoApplication? {
            if (instance != null) return instance
            return null
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}