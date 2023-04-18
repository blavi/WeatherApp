package com.example.weatheralertapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatheralertapp.data.WeatherAlert
import com.example.weatheralertapp.databinding.AlertItemBinding

class AlertsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private val diffCallback = object : DiffUtil.ItemCallback<WeatherAlert>() {
        override fun areItemsTheSame(
            oldItem: WeatherAlert,
            newItem: WeatherAlert
        ): Boolean {
            if (oldItem.javaClass != newItem.javaClass) {
                return false
            }

            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WeatherAlert,
            newItem: WeatherAlert
        ): Boolean {
            if (oldItem.javaClass != newItem.javaClass) {
                return false
            }

            return oldItem.properties.event == newItem.properties.event
                    && oldItem.properties.effective == newItem.properties.effective
                    && oldItem.properties.ends == newItem.properties.ends
                    && oldItem.properties.senderName == newItem.properties.senderName
                    && oldItem.properties.duration == newItem.properties.duration
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        AlertsViewHolder(
            AlertItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AlertsViewHolder) {
            val currentItem = differ.currentList[position]
            holder.bindTo(currentItem)
        }
    }

    fun submitList(list: List<WeatherAlert>, then: () -> Unit = {}) {
        differ.submitList(list) {
            then.invoke()
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}