package com.example.nasagallery.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoListItem(
    modifier: Modifier = Modifier,
    index: Int,
    photo: PhotoDetails,
    onPhotoClicked: (Int) -> Unit
) {
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