package com.example.nasagallery.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.nasagallery.R.drawable
import com.example.nasagallery.ui.theme.Black80

@Composable
fun AppBarImage(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth,
        painter = painterResource(id = drawable.ic_cosmos_vector),
        contentDescription = "",
        colorFilter = ColorFilter.tint(color = Black80, blendMode = BlendMode.SrcAtop)
    )
}