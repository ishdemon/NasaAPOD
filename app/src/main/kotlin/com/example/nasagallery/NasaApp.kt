package com.example.nasagallery

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NasaApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}