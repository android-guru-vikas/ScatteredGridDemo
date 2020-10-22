package com.vikas.scatteredgriddemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DemoApplication : Application() {

    companion object {
        private var instance: DemoApplication? = null

        @Synchronized
        fun getInstance(): DemoApplication? {
            if (instance != null) return instance
            return null
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

//        val bindingComponent = DaggerApp.builder()
//            .application(this)
//            .build()
//
//        DataBindingUtil.setDefaultComponent(bindingComponent)
    }
}