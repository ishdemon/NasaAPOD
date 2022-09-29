package com.example.nasagallery.ui.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun BottomRoundedShape() = MaterialTheme.shapes.extraLarge.copy(
    topStart = CornerSize(0.dp),
    topEnd = CornerSize(0.dp),
    bottomStart = CornerSize(28.dp),
    bottomEnd = CornerSize(28.dp)
)