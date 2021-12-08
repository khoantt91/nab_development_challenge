package com.example.nabchallenge.view.onboarding.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nabchallenge.common.fragment.BaseFragment
import com.example.nabchallenge.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    //region Variables
    private val viewModel: SplashViewModel by viewModels { viewModelFactory }
    //endregion

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)

    //region Init View
    override fun setupView() {
        // Register event listener
        handleLoadPrepareDataEvent()
    }
    //endregion

    //region Update UI

    //endregion

    //region Event Listener
    private fun handleLoadPrepareDataEvent() = viewModel.loadPrepareData().observe(this) { loggedFlag ->
        if (loggedFlag) findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToDashboardNavGraph())
        else findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }
    //endregion

    //region Private Support Methods

    //endregion

}