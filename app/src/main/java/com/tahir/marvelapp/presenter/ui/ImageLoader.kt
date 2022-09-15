package com.tahir.marvelapp.presenter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

/**
 * ImageLoader loads image from url using coil
 */
@Composable
fun ImageLoader(url: String) {


    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = "character Image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.size(75.dp)
    )
}