package com.example.nasagallery.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.nasagallery.common.DataState.Success
import com.example.nasagallery.ui.components.PagerIndicator
import com.example.nasagallery.ui.home.PhotosViewModel
import com.example.nasagallery.ui.theme.NasaGalleryTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class, ExperimentalPagerApi::class)
@Destination
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: PhotosViewModel,
    index: Int,
) {
    //val viewModel: PhotosViewModel = hiltViewModel()
    val photoDetailsState = viewModel.photoDetailsState.collectAsState()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }
    NasaGalleryTheme {
        Scaffold(
            containerColor = Color.Transparent,
        ) {
            val details = photoDetailsState.value as Success
            val pagerState = rememberPagerState(initialPage = index)
            Box(modifier = modifier) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    HorizontalPager(
                        count = details.data.size,
                        state = pagerState,
                        // Add 32.dp horizontal padding to 'center' the pages
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                    ) { page ->
                        Column(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surface)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            DetailImageCard(
                                modifier = Modifier.padding(it),
                                details = details.data[page]
                            )
                            DetailsColumn(details = details.data[page])
                        }
                    }
                }
                PagerIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    indicatorCount = 5,
                    indicatorSize = 8.dp,
                    space = 4.dp,
                    pagerState = pagerState
                )
            }
        }
    }
}