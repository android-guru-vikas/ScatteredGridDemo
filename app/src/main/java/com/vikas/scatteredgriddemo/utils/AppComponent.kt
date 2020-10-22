package com.vikas.scatteredgriddemo.utils

import android.app.Application
import androidx.databinding.DataBindingComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@DataBinding
@Component(modules = [
        AppModule::class
    ]
)
interface AppComponent : DataBindingComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}