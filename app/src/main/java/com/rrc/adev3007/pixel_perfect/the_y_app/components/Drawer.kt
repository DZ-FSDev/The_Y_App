package com.rrc.adev3007.pixel_perfect.the_y_app.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rrc.adev3007.pixel_perfect.the_y_app.session.SessionViewModel


object DrawerState {
    var isDrawerOpen by mutableStateOf(false)

    fun toggleDrawer() {
        isDrawerOpen = !isDrawerOpen
    }
}

@Composable
fun Drawer(viewModel: SessionViewModel) {
    val isDrawerOpen = DrawerState.isDrawerOpen
    AnimatedVisibility(
            visible = isDrawerOpen,
            enter = slideInHorizontally(initialOffsetX = { -it }),
            exit = slideOutHorizontally(targetOffsetX = { -it }),
    ) {
        val darkMode by viewModel.darkMode
        val autoplay by viewModel.autoplay
        val profanityFilter by viewModel.profanityFilter
        val scale by viewModel.scale
        Column(
                modifier =
                        Modifier.zIndex(100f)
                                .fillMaxHeight()
                                .fillMaxWidth(0.5f)
                                .background(
                                        if (darkMode) Color.hsv(0f, 0f, 0.1f, 1f)
                                        else Color.Gray
                                )
        ) {
            DefaultProfileIcon(
                    modifier = Modifier.padding(16.dp),
                    onClick = { DrawerState.toggleDrawer() }
            )

            Text(
                    text = "Settings",
                    modifier = Modifier.padding(horizontal = 19.dp, vertical = 8.dp),
                    fontSize = when (scale) {
                        ScalingLevel.Small -> 15.sp
                        ScalingLevel.Normal -> 20.sp
                        else -> 25.sp
                    },
                    color = if (darkMode) Color.White
                            else Color.Black // Set font color
            )

            ScalingSlider(
                text = "UI Scaling",
                initialLevel = scale,
                onLevelChange = { viewModel.updateScale(it) },
                fontSize = when (scale) {
                    ScalingLevel.Small -> 10.sp
                    ScalingLevel.Normal -> 12.sp
                    else -> 14.sp
                },
                color =
                if (darkMode) Color.White
                else Color.Black // Set font color
            )

            SettingToggle(
                    text = "Dark Mode",
                    fontSize = when (scale) {
                        ScalingLevel.Small -> 10.sp
                        ScalingLevel.Normal -> 12.sp
                        else -> 14.sp
                    },
                    color =
                    if (darkMode) Color.White
                    else Color.Black,
                    initialChecked = darkMode,
                    onCheckedChange = { viewModel.toggleDarkMode() }
            )
            SettingToggle(
                text = "Auto Play",
                initialChecked = autoplay,
                onCheckedChange = { viewModel.toggleAutoplay() },
                fontSize = when (scale) {
                    ScalingLevel.Small -> 10.sp
                    ScalingLevel.Normal -> 12.sp
                    else -> 14.sp
                },
                color = if (darkMode) Color.White
                else Color.Black
            )
            SettingToggle(
                text = "Profanity Filter",
                initialChecked = profanityFilter,
                onCheckedChange = { viewModel.toggleProfanityFilter() },
                fontSize = when (scale) {
                    ScalingLevel.Small -> 10.sp
                    ScalingLevel.Normal -> 12.sp
                    else -> 14.sp
                },
                color = if (darkMode) Color.White
                else Color.Black
            )
        }
    }
}
