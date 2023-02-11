package com.example.binlist.presentation

import android.app.Application
import com.example.binlist.di.DaggerApplicationComponent

class BinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
