package com.example.nasagallery.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.ui.components.CoilImage
import com.example.nasagallery.ui.theme.Black500
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoGrid(
    modifier: Modifier = Modifier,
    gridState: LazyGridState,
    photos: List<PhotoDetails>,
    onPhotoClicked: (Int) -> Unit
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
            state = gridState,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            itemsIndexed(photos) { index, photo ->
                var isLoaded by rememberSaveable { mutableStateOf(false) }
                    Card(
                        shape = MaterialTheme.shapes.extraLarge,
                        elevation = 2.dp,
                        modifier = Modifier
                            .padding(all = 12.dp)
                            .placeholder(
                                visible = !isLoaded,
                                color = Color.Gray,
                                // optional, defaults to RectangleShape
                                shape = MaterialTheme.shapes.extraLarge,
                                highlight = PlaceholderHighlight.shimmer(
                                    highlightColor = Color.White
                                )
                            )
                            .clickable {
                                onPhotoClicked(index)
                            }
                    ) {
                        CoilImage(
                            modifier = Modifier.height(300.dp),
                            imageUrl = photo.url,
                            loadState = {
                                isLoaded = it
                            }
                        )
                    }
            }
        }

    }
}