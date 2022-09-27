package com.example.nasagallery.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.ui.components.CoilImage
import com.example.nasagallery.ui.components.StaggeredVerticalGrid

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoStaggeredGrid(
    modifier: Modifier = Modifier,
    photos: List<PhotoDetails>,
    onPhotoClicked: (PhotoDetails) -> Unit
) {
    LazyColumn {
        item {
            StaggeredVerticalGrid(
                modifier = Modifier.padding(2.dp)
            ) {
                photos.forEach { photo ->
                    Card(
                        shape = shape,
                        elevation = 8.dp,
                        modifier = Modifier.padding(all = 4.dp)
                    ) {
                        CoilImage(
                            imageUrl = photo.url
                        )
                    }
                }
            }
        }
    }
}