package com.rrc.adev3007.pixel_perfect.the_y_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rrc.adev3007.pixel_perfect.the_y_app.R

@Composable
fun ProfileIcon(
    modifier: Modifier,
    drawableRes: Int,
    iconSize: Dp = 32.dp,
    onClick: () -> Unit = {}
) {
    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(iconSize)
            .padding(4.dp)
            .clip(CircleShape)
            .clickable { onClick() }
    )
}

@Preview
@Composable
fun ProfileIconPreview() {
    ProfileIcon(drawableRes = R.drawable.jimmy, modifier = Modifier)
}