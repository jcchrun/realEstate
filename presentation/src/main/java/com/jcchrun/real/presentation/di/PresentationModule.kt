package com.jcchrun.real.presentation.di

import com.jcchrun.real.presentation.detail.RealEstateDetailViewModel
import com.jcchrun.real.presentation.list.RealEstateListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { RealEstateListViewModel(get(), get(), get()) }
    viewModel { RealEstateDetailViewModel(get(), get(), get(), get()) }
}
