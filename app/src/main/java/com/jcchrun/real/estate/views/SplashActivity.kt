package com.jcchrun.real.estate.views

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.jcchrun.real.commons.app.ui.AbstractActivity
import com.jcchrun.real.estate.databinding.ActivitySplashBinding
import com.jcchrun.real.estate.viewmodels.SplashViewModel
import com.jcchrun.real.presentation.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity: AbstractActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uiBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(uiBinding.root)

        splashViewModel.initLiveData.observe(this, {
            redirectToHome()
        })
        splashViewModel.init()
    }

    private fun redirectToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}