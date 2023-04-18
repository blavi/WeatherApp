package com.example.weatheralertapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatheralertapp.data.WeatherAlert
import com.example.weatheralertapp.data.WeatherRepository
import com.example.weatheralertapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private val _alerts = MutableLiveData<MainViewState?>(null)
    val alerts: LiveData<MainViewState?> = _alerts

    fun getAlerts() {
        viewModelScope.launch {
            val results = repository.getAlerts()
            if (results is Resource.Success) {
                _alerts.postValue(
                    MainViewState.Results(results.data.features)
                )
            } else {
                _alerts.postValue(
                    MainViewState.NoResults
                )
            }
        }
    }
}

sealed class MainViewState {
    class Results(val results: List<WeatherAlert>): MainViewState()
    object NoResults: MainViewState()
}