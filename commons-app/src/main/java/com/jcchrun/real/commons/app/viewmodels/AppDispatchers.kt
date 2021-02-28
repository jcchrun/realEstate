package com.jcchrun.real.commons.app.viewmodels

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class AppDispatchers(
    val main: CoroutineDispatcher = Dispatchers.Main,
    val io: CoroutineDispatcher = Dispatchers.IO
) {
}