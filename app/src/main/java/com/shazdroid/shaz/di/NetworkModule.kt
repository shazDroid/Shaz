package com.shazdroid.shaz.di

import com.shazdroid.shaz.api.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    fun providesRetrofit(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gorest.co.in/public-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}