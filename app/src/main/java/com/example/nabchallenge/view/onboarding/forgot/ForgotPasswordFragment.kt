package com.example.nabchallenge.view.onboarding.forgot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nabchallenge.common.fragment.BaseFragment
import com.example.nabchallenge.databinding.FragmentForgotPasswordBinding
import com.example.nabchallenge.utils.onDebounceClick

class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

    //region Variables
    private val viewModel: ForgotPasswordViewModel by viewModels { viewModelFactory }
    //endregion

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentForgotPasswordBinding? = FragmentForgotPasswordBinding.inflate(inflater, container, false)

    //region Init View
    override fun setupView() {
        /* Register Event Handler */
        binding?.btnForgot?.onDebounceClick {
            handleForgotPasswordEvent()
        }
    }
    //endregion

    //region Update UI

    //endregion

    //region Event Listener
    private fun handleForgotPasswordEvent() = viewModel.forgotPassword().observe(this) { hint ->
        showToast(hint)
        findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotFragmentToLoginFragment())
    }
    //endregion

    //region Private Support Methods

    //endregion
}