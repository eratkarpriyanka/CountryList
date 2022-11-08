package com.example.regions.di

import com.example.regions.RegionsRepository
import com.example.regions.RegionsRepositoryImpl
import com.example.regions.network.RegionsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RegionsModule {

    @Singleton
    @Provides
    fun providesRegionsApi(retrofit: Retrofit): RegionsApi =
        retrofit.create(RegionsApi::class.java)

    @Singleton
    @Provides
    fun providesRegionsRepository(regionsApi: RegionsApi): RegionsRepository =
        RegionsRepositoryImpl(regionsApi)
}
