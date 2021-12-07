package com.example.nabchallenge.view.onboarding.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nabchallenge.R
import com.example.nabchallenge.common.fragment.BaseFragment
import com.example.nabchallenge.databinding.FragmentLoginBinding
import com.example.nabchallenge.model.AppError
import com.example.nabchallenge.utils.onDebounceClick

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    //region Variables
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }
    //endregion

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding? = FragmentLoginBinding.inflate(inflater, container, false)

    //region Init View
    override fun setupView() {

        /* Register Observer */
        observerError()

        /* Register Event Handler */
        binding?.tvForgotPassword?.onDebounceClick {
            handleForgotPasswordEvent()
        }

        binding?.btnLogin?.onDebounceClick {
            handleLoginEvent(binding?.edtUsername?.text?.toString(), binding?.edtPassword?.text?.toString())
        }
    }
    //endregion

    //region Update UI
    private fun observerError() = viewModel.errorLogin.observe(this) {
        when (AppError.AppErrorCode.makeFromCode(it.code)) {
            AppError.AppErrorCode.INVALID_USERNAME_ERROR -> binding?.edtUsername?.error = getString(R.string.error_message_invalid_username)
            AppError.AppErrorCode.INVALID_PASSWORD_ERROR -> binding?.edtPassword?.error = getString(R.string.error_message_invalid_password)
            else -> Unit
        }
    }
    //endregion

    //region Event Listener
    private fun handleForgotPasswordEvent() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotFragment())
    }

    private fun handleLoginEvent(userName: String?, password: String?) = viewModel.login(userName, password).observe(this) { success ->
        if (success) findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeNavGraph())
    }
    //endregion

    //region Private Support Methods

    //endregion
}