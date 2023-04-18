package com.example.weatheralertapp.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.weatheralertapp.data.WeatherAlert
import com.example.weatheralertapp.WeatherAlertComponent
import com.example.weatheralertapp.databinding.AlertItemBinding

class AlertsViewHolder(
    private val binding: AlertItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(item: WeatherAlert) {
//        binding.eventName.text = item.properties.event
//        binding.startDate.text = item.properties.effective
//        binding.endDate.text = item.properties.ends
//        binding.sender.text = item.properties.senderName
//        binding.duration.text = item.properties.duration.toString()
//
//        val url = "https://picsum.photos/200/200/?temp=" + item.properties.imageRandomKey
//        Glide.with(binding.alertImage.context)
//            .load(url)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
////            .error(R.drawable.ic_data_not_found)
//            .apply(
//                RequestOptions().transform(
//                    RoundedCorners(8)
//                )
//            )
//            .into(binding.alertImage)

        binding.alertComponent.setContent {
            WeatherAlertComponent(item = item)
        }
    }
}