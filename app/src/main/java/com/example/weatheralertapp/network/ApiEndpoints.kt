package com.example.weatheralertapp.network

import com.example.weatheralertapp.data.WeatherAlertsResponse
import retrofit2.http.GET

interface ApiEndpoints {
    @GET("/alerts/active?status=actual&message_type=alert")
    suspend fun getAlerts(): WeatherAlertsResponse
}