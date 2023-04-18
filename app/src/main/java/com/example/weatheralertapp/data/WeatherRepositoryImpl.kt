package com.example.weatheralertapp.data

import com.example.weatheralertapp.network.ApiEndpoints
import com.example.weatheralertapp.utils.Resource
import com.example.weatheralertapp.utils.handleRequest
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: ApiEndpoints,
    private val mapper: WeatherAlertMapper
): WeatherRepository {

    override suspend fun getAlerts(): Resource<WeatherAlerts>? {
        val response = handleRequest(
            requestFunc = {
                api.getAlerts()
            },
            convertRsp = {
                mapper.mapToLocalModel(it)
            }
        )
        return response
    }
}
