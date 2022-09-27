package com.example.nasagallery.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.nasagallery.R

@ExperimentalCoilApi
@Composable
fun CoilImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    placeholder: Int = R.drawable.image_placeholder,
    contentDescription: String = "",
    isSquare: Boolean = false,
    contentScale: ContentScale = ContentScale.Crop
) {
    if (isSquare) modifier.size(200.dp)
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .placeholder(placeholder)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
    )
}