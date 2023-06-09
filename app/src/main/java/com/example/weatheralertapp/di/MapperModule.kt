package com.example.weatheralertapp.di

import com.example.weatheralertapp.data.WeatherAlertMapper
import com.example.weatheralertapp.data.WeatherAlertMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Singleton
    @Binds
    abstract fun bindRepo(
        repoImpl: WeatherAlertMapperImpl
    ): WeatherAlertMapper
}