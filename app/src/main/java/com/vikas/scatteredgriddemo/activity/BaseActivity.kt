package com.vikas.scatteredgriddemo.activity

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.vikas.scatteredgriddemo.*
import com.vikas.scatteredgriddemo.databinding.ActivityBaseBinding
import com.vikas.scatteredgriddemo.model.LiciousViewModel
import com.vikas.scatteredgriddemo.model.LiciousViewModelFactory
import com.vikas.scatteredgriddemo.utils.AppToast
import timber.log.Timber

abstract class BaseActivity<VM : ViewModel?> : AppCompatActivity() {
    @JvmField
    var handler = Handler()

    @JvmField
    var pContext: Context? = null
    var pTAG = Activity::class.java.name

    @JvmField
    var pAppToast: AppToast? = null

    @JvmField
    var viewModel: ViewModel? = null
    private var binding: ActivityBaseBinding? = null
    var liciousViewModel: LiciousViewModel? = null

    protected abstract fun getViewModel(): Class<VM>?


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base)


        val sparkViewModelFactory =
            LiciousDemoApplication.getInstance()?.let {
                LiciousViewModelFactory(
                    it
                )
            }
        viewModel = ViewModelProviders.of(this, sparkViewModelFactory)[getViewModel() as Class<VM>]

        liciousViewModel = viewModel as LiciousViewModel?

        initObjects()
        hideKeyboard()

        Timber.d("Inside BaseActivity")
        changeWindowStatusBarColor()
    }

    fun hideKeyboard() {
        try {
            runOnUiThread {
                var imm: InputMethodManager? = null
                try {
                    imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    assert(imm != null)
                    if (currentFocus != null && currentFocus?.windowToken != null) {
                        imm.hideSoftInputFromWindow(
                            currentFocus?.windowToken,
                            InputMethodManager.HIDE_NOT_ALWAYS
                        )
                    }
                } catch (e: Exception) {
                    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
                }
            }
        } catch (e: Exception) {
            Timber.d("Inside exception of hide keyboard : " + e.message)
        }
    }

    private fun changeWindowStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.colorPrimary)
        }
    }

    private fun initObjects() {
        pContext = this
        pAppToast = AppToast.getInstance()
    }
}