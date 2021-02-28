package com.jcchrun.real.presentation

import android.os.Bundle
import com.jcchrun.real.commons.app.ui.AbstractActivity

class HomeActivity: AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}