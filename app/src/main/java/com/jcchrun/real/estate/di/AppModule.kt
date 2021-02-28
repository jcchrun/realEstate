package com.jcchrun.real.estate.di

import com.jcchrun.real.estate.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SplashViewModel(get()) }
}
