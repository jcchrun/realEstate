package com.jcchrun.real.commons.app.di

import com.jcchrun.real.commons.app.helpers.NetworkHelper
import com.jcchrun.real.commons.app.viewmodels.AppDispatchers
import org.koin.dsl.module

val commonsAppModule = module {

    factory { AppDispatchers() }
    single { NetworkHelper(get()) }
}