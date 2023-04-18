package com.example.weatheralertapp.data

import com.example.weatheralertapp.utils.Resource

interface WeatherRepository {
    suspend fun getAlerts(): Resource<WeatherAlerts>?
}