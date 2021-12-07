package com.example.nabchallenge.common.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.nabchallenge.App
import com.example.nabchallenge.R
import com.example.nabchallenge.common.activity.dj.DaggerActivityComponent
import com.example.nabchallenge.common.viewmodel.ViewModelFactory
import javax.inject.Inject

@Suppress("MemberVisibilityCanBePrivate", "UNCHECKED_CAST", "HasPlatformType", "unused")
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    //region Variables
    lateinit var binding: VB

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    //endregion

    abstract fun provideBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupInjection()
        setupView()
    }

    /***
     *   Setup UI on the first time you launch this screen
     */
    abstract fun setupView()

    //region Update UI
    fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun showToast(resource: Int) = Toast.makeText(this, getString(resource), Toast.LENGTH_SHORT).show()

    fun showDialog(
        title: String, message: String,
        titleConfirmButton: String = getString(R.string.common_ok), titleCancelButton: String = getString(R.string.common_cancel),
        onConfirmed: (() -> Unit)? = null, onCanceled: (() -> Unit)? = null
    ) {
        val dialog = AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(titleConfirmButton) { _, _ -> onConfirmed?.invoke() }
            setNegativeButton(titleCancelButton) { _, _ -> onCanceled?.invoke() }
        }
        dialog.show()
    }
    //endregion

    //region Event Listener

    //endregion

    //region Private Support Methods
    private fun setupBinding() {
        binding = provideBinding(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupInjection() {
        DaggerActivityComponent.factory().create((applicationContext as App).appComponent).inject(this as BaseActivity<ViewBinding>)
    }
    //endregion

}