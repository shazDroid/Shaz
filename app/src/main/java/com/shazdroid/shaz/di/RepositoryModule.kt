package com.shazdroid.shaz.di

import com.shazdroid.shaz.api.ApiInterface
import com.shazdroid.shaz.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module

object RepositoryModule {

    @Provides
    fun providesRepository(apiInterface: ApiInterface): Repository {
        return Repository(apiInterface)
    }
}