package com.example.nabchallenge.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.nabchallenge.App
import com.example.nabchallenge.R
import com.example.nabchallenge.common.activity.BaseActivity
import com.example.nabchallenge.common.fragment.dj.DaggerFragmentComponent
import com.example.nabchallenge.common.viewmodel.ViewModelFactory
import javax.inject.Inject

@Suppress("MemberVisibilityCanBePrivate", "UNCHECKED_CAST", "HasPlatformType", "unused")
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    //region Variables
    var binding: VB? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    //endregion

    abstract fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): VB?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = binding.let {
        setupInjection()
        setupBinding(inflater, container).apply { setupView() }
    } ?: binding!!.root

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    /***
     *   Setup UI on the first time you launch this screen
     */
    abstract fun setupView()

    //region Update UI
    fun showToast(message: String) = (activity as? BaseActivity<*>)?.showToast(message)

    fun showToast(resource: Int) = (activity as? BaseActivity<*>)?.showToast(resource)

    fun showDialog(
        title: String, message: String,
        titleConfirmButton: String = getString(R.string.common_ok), titleCancelButton: String = getString(R.string.common_cancel),
        onConfirmed: (() -> Unit)? = null, onCanceled: (() -> Unit)? = null
    ) = (activity as? BaseActivity<*>)?.showDialog(title, message, titleConfirmButton, titleCancelButton, onConfirmed, onCanceled)
    //endregion

    //region Event Listener

    //endregion

    //region Private Support Methods
    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        binding = provideBinding(inflater, container)
        return binding?.root
    }

    private fun setupInjection() {
        DaggerFragmentComponent.factory().create((activity?.application as App).appComponent).inject(this as BaseFragment<ViewBinding>)
    }
    //endregion

}