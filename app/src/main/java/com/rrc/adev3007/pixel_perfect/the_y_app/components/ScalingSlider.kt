package com.rrc.adev3007.pixel_perfect.the_y_app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class ScalingLevel {
    Small, Normal, Large
}

@Composable
fun ScalingSlider(
    text: String,
    initialLevel: ScalingLevel = ScalingLevel.Normal,
    onLevelChange: (ScalingLevel) -> Unit
) {
    var selectedLevel by remember { mutableStateOf(initialLevel) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Text(
                text = "$text:  $selectedLevel"
            )

            Slider(
                value = when (selectedLevel) {
                    ScalingLevel.Small -> 0f
                    ScalingLevel.Normal -> 1f
                    ScalingLevel.Large -> 2f
                },
                onValueChange = { newValue ->
                    selectedLevel = when {
                        newValue <= 0.5f -> ScalingLevel.Small
                        newValue <= 1.5f -> ScalingLevel.Normal
                        else -> ScalingLevel.Large
                    }
                    onLevelChange(selectedLevel)
                },
                valueRange = 0f..2f,
                steps = 2,
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
            )
        }
    }
}
