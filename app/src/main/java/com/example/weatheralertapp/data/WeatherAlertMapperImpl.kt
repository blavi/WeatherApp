package com.example.weatheralertapp.data

import javax.inject.Inject

class WeatherAlertMapperImpl @Inject constructor(): WeatherAlertMapper {

    override fun mapToLocalModel(response: WeatherAlertsResponse): WeatherAlerts =
        WeatherAlerts(
            features = response.features.map {
                it.asLocalModel(response.features.size)
            }
        )
}