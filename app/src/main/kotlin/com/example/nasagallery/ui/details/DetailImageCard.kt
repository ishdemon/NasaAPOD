package com.example.nasagallery.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nasagallery.R
import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.ui.components.CoilImage
import com.example.nasagallery.ui.components.FieldState
import com.example.nasagallery.ui.theme.BottomRoundedShape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun ColumnScope.DetailImageCard(
    modifier: Modifier = Modifier,
    details: PhotoDetails,
    isFullScreen: FieldState<Boolean>
) {
    var isClicked = isFullScreen.valueState.value
    var isLoaded by rememberSaveable { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = modifier
            .placeholder(
                visible = !isLoaded,
                color = Color.Gray,
                // optional, defaults to RectangleShape
                shape = BottomRoundedShape(),
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = Color.White
                )
            )
            .weight(1f),
        shape = BottomRoundedShape()
    ) {
        Box {
            CoilImage(
                modifier = Modifier.fillMaxSize(),
                imageUrl = details.hdUrl,
                loadState = {
                    isLoaded = it
                }
            )

            IconButton(
                onClick = {
                    isClicked = !isClicked
                    isFullScreen.updateValue(isClicked)
                },
                modifier = modifier.size(56.dp).align(Alignment.BottomEnd).padding(end = 4.dp, top = 4.dp)
            ) {
                if (isFullScreen.valueState.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fullscreen_exit),
                        modifier = Modifier,
                        contentDescription = "",
                        tint = Color.White
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fullscreen),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}