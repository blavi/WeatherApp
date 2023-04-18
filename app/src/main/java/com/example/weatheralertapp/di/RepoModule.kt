package com.example.weatheralertapp.di

import com.example.weatheralertapp.data.WeatherRepository
import com.example.weatheralertapp.data.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindRepo(
        repoImpl: WeatherRepositoryImpl
    ): WeatherRepository
}