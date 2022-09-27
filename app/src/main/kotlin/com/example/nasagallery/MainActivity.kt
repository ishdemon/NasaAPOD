package com.example.nasagallery

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nasagallery.common.hideSystemUI
import com.example.nasagallery.ui.home.HomeScreen
import com.example.nasagallery.ui.theme.NasaGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        //hideSystemUI()
        setContent {

            NasaGalleryTheme {
                HomeScreen()
                // A surface container using the 'background' color from the theme
            }
        }
    }
}