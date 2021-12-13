package com.example.nabchallenge.view.dashboard

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nabchallenge.R
import com.example.nabchallenge.common.fragment.BaseFragment
import com.example.nabchallenge.databinding.FragmentDashboardBinding
import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.utils.log.wLog
import com.example.nabchallenge.utils.onDebounceClick
import com.example.nabchallenge.utils.security.KeystoreCrypto

class DashboardFragment : BaseFragment<FragmentDashboardBinding>(), WeatherInfoRecyclerAdapter.WeatherInfoRecyclerAdapterListener {

    //region Variables
    private val viewModel: DashboardViewModel by viewModels { viewModelFactory }
    private val weatherInfoAdapter: WeatherInfoRecyclerAdapter by lazy { WeatherInfoRecyclerAdapter(this) }
    //endregion

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)

    //region Init View
    override fun setupView() {

        // Init view
        binding?.toolbar?.apply {
            setTitle(R.string.dashboard_weather_title)
            inflateMenu(R.menu.dashboard_menu)
            setOnMenuItemClickListener { handleMenuClickEvent(it) }
        }

        binding?.rvWeather?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = weatherInfoAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        // Register event listener
        binding?.btnGetWeather?.onDebounceClick { handleSearchWeatherEvent(binding?.edtSearchKey?.text.toString()) }

        // Register observer
        observerGetWeatherStateLive()
    }
    //endregion

    //region Update UI

    private fun observerGetWeatherStateLive() {
        viewModel.getWeatherStateLive.observe(this) { weatherState ->

            // Show hide loading
            when (weatherState.isLoading) {
                true -> binding?.progressIndicator?.show()
                else -> binding?.progressIndicator?.hide()
            }

            // Show error
            weatherState.error?.let { showToast(it.message.toString()) }

            // Update adapter
            weatherState.weatherList?.let { weatherInfoAdapter.notifyChangedDiffUtil(it) }
        }
    }
    //endregion

    //region Event Listener
    private fun handleSearchWeatherEvent(key: String) {
        if (key.length < 3) {
            binding?.edtSearchKey?.error = getString(R.string.error_message_type_at_least_three_character)
            binding?.edtSearchKey?.requestFocus()
            return
        } else {
            binding?.edtSearchKey?.error = null
        }

        hideKeyboard()
        viewModel.searchWeatherInfo(key)
    }

    override fun onWeatherClick(item: WeatherInfo, position: Int) {
        viewModel.clickWeatherInfo(item)
    }

    private fun handleMenuClickEvent(menu: MenuItem): Boolean {
        if (menu.itemId == R.id.actionLogout) viewModel.logout().observe(viewLifecycleOwner) { success ->
            if (success) findNavController().navigate(DashboardFragmentDirections.actionGlobalToSplashFragment())
        }
        return true
    }
    //endregion

    //region Private Support Methods

    //endregion
}