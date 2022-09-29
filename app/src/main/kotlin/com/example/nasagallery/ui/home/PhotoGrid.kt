package com.example.nasagallery.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.ui.components.PhotoListItem
import com.example.nasagallery.ui.theme.Black500

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
                PhotoListItem(
                    index = index,
                    photo = photo,
                    onPhotoClicked = onPhotoClicked
                )
            }
        }
    }
}