package com.example.nasagallery.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.nasagallery.common.DataState.Empty
import com.example.nasagallery.common.DataState.Error
import com.example.nasagallery.common.DataState.Loading
import com.example.nasagallery.common.DataState.Success
import com.example.nasagallery.ui.components.FullScreenLoader
import com.example.nasagallery.ui.components.GenericErrorMessage
import com.example.nasagallery.ui.destinations.DetailsScreenDestination
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: PhotosViewModel,
    navigator: DestinationsNavigator
) {
    //val viewModel: PhotosViewModel = hiltViewModel()
    val photoDetailsState = viewModel.photoDetailsState.collectAsState()
    val systemUiController = rememberSystemUiController()
    val scrollState = rememberLazyGridState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }
    AppBarImage()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.Transparent,
        topBar = {
            HomeAppBar(scrollBehavior = scrollBehavior)
        }
    ) {
        when (val state = photoDetailsState.value) {
            Empty -> {
                GenericErrorMessage(
                    modifier = Modifier.padding(it)
                )
            }
            is Error -> {
                GenericErrorMessage(
                    modifier = Modifier.padding(it)
                )
            }
            Loading -> {
                FullScreenLoader(
                    modifier = Modifier.padding(it)
                )
            }
            is Success -> {
                PhotoGrid(
                    gridState = scrollState,
                    modifier = modifier
                        .padding(paddingValues = it),
                    photos = state.data,
                    onPhotoClicked = { index ->
                        navigator.navigate(DetailsScreenDestination(index))
                    }
                )
            }
        }
    }
}