package com.example.weatheralertapp.data

interface WeatherAlertMapper {

    fun mapToLocalModel(response: WeatherAlertsResponse): WeatherAlerts
}