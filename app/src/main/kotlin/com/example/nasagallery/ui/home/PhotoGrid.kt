package com.example.nasagallery.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.ui.components.CoilImage
import com.example.nasagallery.ui.theme.Black500

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoGrid(
    modifier: Modifier = Modifier,
    photos: List<PhotoDetails>,
    onPhotoClicked: (PhotoDetails) -> Unit
) {
    Card(
        modifier = modifier,
        elevation = 16.dp,
        shape = MaterialTheme.shapes.extraLarge.copy(
            topStart = CornerSize(28.dp),
            topEnd = CornerSize(28.dp),
            bottomStart = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp)
        ),
        backgroundColor = Black500,
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(photos) { photo ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        shape = MaterialTheme.shapes.extraLarge,
                        elevation = 8.dp,
                        modifier = Modifier.padding(all = 8.dp)
                    ) {
                        CoilImage(
                            modifier = Modifier.height(300.dp),
                            imageUrl = photo.url
                        )
                    }
                }
            }
        }

    }
}