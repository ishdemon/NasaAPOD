package com.example.nasagallery

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nasagallery.common.hideSystemUI
import com.example.nasagallery.ui.NavGraphs
import com.example.nasagallery.ui.home.PhotosViewModel
import com.example.nasagallery.ui.theme.NasaGalleryTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            NasaGalleryTheme {
                val engine = rememberAnimatedNavHostEngine(
                    rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING,
                )
                val navController = engine.rememberNavController()
                DestinationsNavHost(
                    engine = engine,
                    navController = navController,
                    navGraph = NavGraphs.root,
                    dependenciesContainerBuilder = {
                        dependency(hiltViewModel<PhotosViewModel>(this@MainActivity))
                    }
                )
                //HomeScreen()
                // A surface container using the 'background' color from the theme
            }
        }
    }
}