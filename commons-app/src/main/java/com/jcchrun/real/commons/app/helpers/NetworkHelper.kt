package com.jcchrun.real.commons.app.helpers

import android.content.Context
import com.jcchrun.real.commons.app.extensions.isConnected

class NetworkHelper(private val context: Context) {

    fun isConnected() = context.isConnected
}