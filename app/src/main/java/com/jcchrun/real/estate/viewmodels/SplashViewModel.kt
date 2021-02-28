package com.jcchrun.real.estate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcchrun.real.commons.app.viewmodels.AbstractViewModel
import com.jcchrun.real.commons.app.viewmodels.AppDispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    appDispatchers: AppDispatchers
): AbstractViewModel(appDispatchers) {

    private val _initLiveData = MutableLiveData<Boolean>()
    val initLiveData: LiveData<Boolean> = _initLiveData

    fun init() {
        mainScope.launch {
            ioScope.async { delay(3000) }.await()
            _initLiveData.value = true
        }
    }
}