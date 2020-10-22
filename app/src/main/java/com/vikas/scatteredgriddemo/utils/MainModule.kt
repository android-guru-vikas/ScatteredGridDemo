package com.vikas.scatteredgriddemo.utils

import com.vikas.scatteredgriddemo.activity.MainActivity
import com.vikas.scatteredgriddemo.adapter.MainAdapter
import com.vikas.scatteredgriddemo.adapter.UserHomeItemsAdapter
import com.vikas.scatteredgriddemo.backend.NetworkService
import com.vikas.scatteredgriddemo.model.MainDataSourceClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainModule
{
    @Provides
    @ActivityRetainedScoped
    fun provideMainApi(retrofit: Retrofit) : NetworkService = retrofit.create(NetworkService::class.java)

    @Provides
    @ActivityRetainedScoped
    fun provideMainAdapter() = MainAdapter(MainActivity())

    @Provides
    @ActivityRetainedScoped
    fun provideUserItemAdapter() = UserHomeItemsAdapter(MainActivity())

    @Provides
    @ActivityRetainedScoped
    fun provideMenRepository(mainApi: NetworkService) = MainDataSourceClass(mainApi)
}