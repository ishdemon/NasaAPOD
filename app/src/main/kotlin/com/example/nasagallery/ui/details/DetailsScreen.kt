package com.example.nasagallery.ui.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.example.nasagallery.common.DataState.Success
import com.example.nasagallery.ui.components.CoilImage
import com.example.nasagallery.ui.components.PagerIndicator
import com.example.nasagallery.ui.home.PhotosViewModel
import com.example.nasagallery.ui.theme.BottomRoundedShape
import com.example.nasagallery.ui.theme.NasaGalleryTheme
import com.example.nasagallery.ui.theme.lightWhite
import com.example.nasagallery.ui.theme.white200
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class, ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Destination
@Composable
fun AnimatedVisibilityScope.DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: PhotosViewModel,
    index: Int,
) {
    //val viewModel: PhotosViewModel = hiltViewModel()
    val photoDetailsState = viewModel.photoDetailsState.collectAsState()
    val systemUiController = rememberSystemUiController()
    val scrollState = rememberLazyGridState()

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
            Box(modifier = Modifier) {
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
                        var isLoaded by rememberSaveable { mutableStateOf(false) }
                        Column(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surface)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Card(
                                elevation = CardDefaults.elevatedCardElevation(4.dp),
                                modifier = Modifier
                                    .placeholder(
                                        visible = !isLoaded,
                                        color = Color.Gray,
                                        // optional, defaults to RectangleShape
                                        shape = BottomRoundedShape(),
                                        highlight = PlaceholderHighlight.shimmer(
                                            highlightColor = Color.White
                                        )
                                    )
                                    .weight(1f)
                                    .padding(it),
                                shape = BottomRoundedShape()
                            ) {
                                CoilImage(
                                    modifier = Modifier.fillMaxSize(),
                                    imageUrl = details.data[page].hdUrl,
                                    loadState = {
                                        isLoaded = it
                                    }
                                )
                            }

                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(24.dp)
                            ) {
                                Text(
                                    text = details.data[page].title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontSize = 22.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Start
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        tint = white200,
                                        contentDescription = null
                                    )
                                    Text(
                                        text = details.data[page].copyright,
                                        style = MaterialTheme.typography.displaySmall,
                                        color = white200,
                                        textAlign = TextAlign.Start
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .height(140.dp)
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Text(
                                        overflow = TextOverflow.Visible,
                                        text = details.data[page].info,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = lightWhite,
                                        textAlign = TextAlign.Start
                                    )
                                }
                            }
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