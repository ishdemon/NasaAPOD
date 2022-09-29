package com.example.nasagallery.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.nasagallery.R
import com.example.nasagallery.common.DataState.Empty
import com.example.nasagallery.common.DataState.Error
import com.example.nasagallery.common.DataState.Loading
import com.example.nasagallery.common.DataState.Success
import com.example.nasagallery.ui.components.FullScreenLoader
import com.example.nasagallery.ui.components.GenericErrorMessage
import com.example.nasagallery.ui.destinations.DetailsScreenDestination
import com.example.nasagallery.ui.theme.Black80
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
    Image(
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth,
        painter = painterResource(id = R.drawable.ic_cosmos_vector),
        contentDescription = "",
        colorFilter = ColorFilter.tint(color = Black80, blendMode = BlendMode.SrcAtop)
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.Transparent,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier,
                title = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "NASA APOD",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            fontSize = 28.sp,
                            textAlign = TextAlign.Center
                        )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(
                        colors = IconButtonDefaults.outlinedIconButtonColors(contentColor = Color.White),
                        onClick = {/* Do Something*/ }) {
                        Icon(Icons.Outlined.Menu, null)
                    }
                }, actions = {
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Outlined.Search, null)
                    }
                })
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