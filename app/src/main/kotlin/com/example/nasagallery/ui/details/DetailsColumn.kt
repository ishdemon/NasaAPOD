package com.example.nasagallery.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.ui.theme.lightWhite
import com.example.nasagallery.ui.theme.white200

@Composable
fun DetailsColumn(
    modifier: Modifier = Modifier,
    details: PhotoDetails
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(24.dp)
    ) {
        Text(
            text = details.title,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 22.sp,
            color = Color.White,
            textAlign = TextAlign.Start
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Outlined.Person,
                tint = white200,
                contentDescription = null
            )
            Text(
                text = details.copyright,
                style = MaterialTheme.typography.displaySmall,
                color = white200,
                textAlign = TextAlign.Start
            )
        }
        Column(
            modifier = Modifier
                .height(140.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                overflow = TextOverflow.Visible,
                text = details.info,
                style = MaterialTheme.typography.labelMedium,
                color = lightWhite,
                textAlign = TextAlign.Start
            )
        }
    }
}