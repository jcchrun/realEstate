package com.jcchrun.real.commons.app.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class AbstractViewModel(
    private val appDispatchers: AppDispatchers
): ViewModel() {

    private val viewModelJob = SupervisorJob()
    protected val mainScope = CoroutineScope(appDispatchers.main + viewModelJob)
    protected val ioScope = CoroutineScope(appDispatchers.io + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        mainScope.cancel()
        ioScope.cancel()
    }
}