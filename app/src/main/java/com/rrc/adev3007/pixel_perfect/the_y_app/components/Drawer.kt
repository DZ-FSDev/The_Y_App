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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rrc.adev3007.pixel_perfect.the_y_app.session.Session
import androidx.compose.ui.platform.LocalContext


object DrawerState {
    var isDrawerOpen by mutableStateOf(false)

    fun toggleDrawer() {
        isDrawerOpen = !isDrawerOpen
    }
}

@Composable
fun Drawer(onStateChanged: () -> Unit) {
    var session = Session.getInstance(LocalContext.current);

    val isDrawerOpen = DrawerState.isDrawerOpen
    AnimatedVisibility(
            visible = isDrawerOpen,
            enter = slideInHorizontally(initialOffsetX = { -it }),
            exit = slideOutHorizontally(targetOffsetX = { -it }),
    ) {
        var darkMode by remember { mutableStateOf(session!!.getBoolean("darkMode", false)) }
        var autoplay by remember { mutableStateOf(session!!.getBoolean("autoplay",false)) }
        var profanityFilter by remember { mutableStateOf(session!!.getBoolean("profanityFilter",false)) }
        var scale by remember {
            mutableStateOf(
                ScalingLevel.valueOf(session?.getString("scale", ScalingLevel.Normal.toString()) ?: ScalingLevel.Normal.toString())
            )
        }

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
                    fontSize = if (session!!.getString("scale", "") == ScalingLevel.Small.toString()) 15.sp
                            else if (session!!.getString("scale", "") == ScalingLevel.Normal.toString()) 20.sp
                            else 25.sp,
                    color = if (session!!.getBoolean("darkMode", false)) Color.White
                            else Color.Black // Set font color
            )

            ScalingSlider(
                text = "UI Scaling",
                initialLevel = scale,
                onLevelChange = {
                    scale = it
                    session!!.putString("scale", it.toString())
                },
                fontSize =
                if (session!!.getString("scale", "") == ScalingLevel.Small.toString()) 10.sp
                else if (session!!.getString("scale", "") == ScalingLevel.Normal.toString()) 12.sp
                else 14.sp,
                color =
                if (session!!.getBoolean("darkMode", false)) Color.White
                else Color.Black // Set font color
            )

            SettingToggle(
                    text = "Dark Mode",
                    fontSize =
                    if (session!!.getString("scale", "") == ScalingLevel.Small.toString()) 10.sp
                    else if (session!!.getString("scale", "") == ScalingLevel.Normal.toString()) 12.sp
                    else 14.sp,
                    color =
                    if (session!!.getBoolean("darkMode", false)) Color.White
                    else Color.Black,
                    initialChecked = darkMode,
                    onCheckedChange = {
                        darkMode = it
                        session!!.putBoolean("darkMode", it)
                        onStateChanged()
                    }
            )
            SettingToggle(
                text = "Auto Play",
                initialChecked = autoplay,
                onCheckedChange = {
                    autoplay = it
                    session!!.putBoolean("autoplay", it)
                },
                fontSize = if (session!!.getString("scale", "") == ScalingLevel.Small.toString()) 10.sp
                else if (session!!.getString("scale", "") == ScalingLevel.Normal.toString()) 12.sp
                else 14.sp,
                color = if (session!!.getBoolean("darkMode", false)) Color.White
                else Color.Black
            )
            SettingToggle(
                text = "Profanity Filter",
                initialChecked = profanityFilter,
                onCheckedChange = {
                    profanityFilter = it
                    session!!.putBoolean("profanityFilter", it)
                },
                fontSize = if (session!!.getString("scale", "") == ScalingLevel.Small.toString()) 10.sp
                else if (session!!.getString("scale", "") == ScalingLevel.Normal.toString()) 12.sp
                else 14.sp,
                color = if (session!!.getBoolean("darkMode", false)) Color.White
                else Color.Black
            )
        }
    }
}
