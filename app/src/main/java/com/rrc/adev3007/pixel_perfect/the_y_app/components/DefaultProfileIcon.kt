package com.rrc.adev3007.pixel_perfect.the_y_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rrc.adev3007.pixel_perfect.the_y_app.R

@Composable
fun DefaultProfileIcon(
    modifier: Modifier,
    iconSize: Dp = 32.dp,
    backgroundColor: Color = Color.Gray,
    onClick: () -> Unit = {}
) {
    Image(
        painter = painterResource(id = R.drawable.person),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(iconSize)
            .background(color = backgroundColor, shape = CircleShape)
            .clickable { onClick() }
    )
}

@Preview
@Composable
fun DefaultProfileIconPreview() {
    DefaultProfileIcon(modifier = Modifier)
}