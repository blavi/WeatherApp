package com.example.weatheralertapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatheralertapp.data.WeatherAlert
import com.example.weatheralertapp.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragmentBinding
    private val alertsAdapter by lazy {
        AlertsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onResume() {
        super.onResume()

        setViewData()
        setObserver()
        viewModel.getAlerts()
    }

    private fun setViewData() {
        binding.rvContent.layoutManager = LinearLayoutManager(context)
        binding.rvContent.adapter = alertsAdapter
    }

    private fun setObserver() {
        viewModel.alerts.observe(viewLifecycleOwner) { state ->
            when (state) {
                is MainViewState.Results -> {
                    displayContent(state.results)
                    toggleMessageVisibility(View.GONE)
                }
                else -> {
                    toggleMessageVisibility(View.VISIBLE)
                }
            }

        }
    }

    private fun displayContent(results: List<WeatherAlert>) {
        alertsAdapter.submitList(results)
    }

    private fun toggleMessageVisibility(visibility: Int) {
        binding.noAlerts.visibility = visibility
    }
}