package com.example.weatheralertapp.data

import com.example.weatheralertapp.calculateDuration
import com.example.weatheralertapp.generateRandom
import com.example.weatheralertapp.getFormattedDate

data class WeatherAlertsResponse(
    val features: List<WeatherAlertDTO>
)

data class WeatherAlertDTO(
    val properties: AlertDetailsDTO
) {
    fun asLocalModel(alertsSize: Int) =
        WeatherAlert(
            properties = properties.asLocalModel(alertsSize)
        )
}

data class AlertDetailsDTO(
    val effective: String,
    val ends: String?,
    val event: String,
    val senderName: String
) {
    fun asLocalModel(alertsSize: Int) =
        AlertDetails(
            effective = getFormattedDate(effective),
            ends = getFormattedDate(ends),
            event = event,
            senderName = senderName,
            duration = calculateDuration(effective, ends),
            imageRandomKey = generateRandom(alertsSize)
        )
}

data class WeatherAlerts(
    val features: List<WeatherAlert>
)

data class WeatherAlert(
    val properties: AlertDetails
)

data class AlertDetails(
    val effective: String,
    val ends: String?,
    val event: String,
    val senderName: String,
    val duration: String,
    val imageRandomKey: Int
)


