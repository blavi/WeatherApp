package com.example.weatheralertapp.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.weatheralertapp.data.WeatherAlert
import com.example.weatheralertapp.databinding.AlertItemBinding

class AlertsViewHolder(
    private val binding: AlertItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(item: WeatherAlert) {
        binding.alertComponent.setContent {
            WeatherAlertComponent(item = item)
        }
    }
}