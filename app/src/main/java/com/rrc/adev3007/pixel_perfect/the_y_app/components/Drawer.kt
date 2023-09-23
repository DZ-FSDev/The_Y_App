package com.rrc.adev3007.pixel_perfect.the_y_app.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

object DrawerState {
    var isDrawerOpen by mutableStateOf(false)

    fun toggleDrawer() {
        isDrawerOpen = !isDrawerOpen
    }
}

@Composable
fun Drawer() {
    val isDrawerOpen = DrawerState.isDrawerOpen
    AnimatedVisibility(
        visible = isDrawerOpen,
        enter = slideInHorizontally(initialOffsetX = { -it }),
        exit = slideOutHorizontally(targetOffsetX = { -it }),
    ) {
        Column(
            modifier = Modifier
                .zIndex(1f)
                .fillMaxHeight()
                .width(250.dp)
                .background(Color.DarkGray)
        ) {
            Text(
                text = "Drawer Content",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Item 1",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {}
            )
            Text(
                text = "Item 2",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {}
            )
            Text(
                text = "Item 3",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {}
            )
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    DrawerState.toggleDrawer()
    Drawer()
}
