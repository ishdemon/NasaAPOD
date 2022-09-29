package com.example.nasagallery.ui.components

import androidx.compose.animation.AnimatedContentScope.SlideDirection
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import com.ramcosta.composedestinations.animations.defaults.DestinationEnterTransition
import com.ramcosta.composedestinations.animations.defaults.DestinationExitTransition
import com.ramcosta.composedestinations.animations.defaults.NavGraphDefaultAnimationParams
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations

@ExperimentalAnimationApi
class DefaultAnimations(
    override val enterTransition: DestinationEnterTransition? = null,
    override val exitTransition: DestinationExitTransition? = null,
    override val popEnterTransition: DestinationEnterTransition? = enterTransition,
    override val popExitTransition: DestinationExitTransition? = exitTransition,
) : NavGraphDefaultAnimationParams {
    companion object {
        val ACCOMPANIST_SLIDE by lazy {
            RootNavGraphDefaultAnimations(
                enterTransition = { slideIntoContainer(SlideDirection.Left, animationSpec = tween(700)) },
                exitTransition = { slideOutOfContainer(SlideDirection.Right, animationSpec = tween(700)) }
            )
        }
    }
}