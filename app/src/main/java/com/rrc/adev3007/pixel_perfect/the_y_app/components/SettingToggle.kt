package com.rrc.adev3007.pixel_perfect.the_y_app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit


@Composable
fun SettingToggle(
    text: String,
    initialChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    fontSize: TextUnit,
    color: Color
) {
    var checked by remember { mutableStateOf(initialChecked) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                checked = !checked
                onCheckedChange(checked)
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            fontSize = fontSize,
            color = color,
        )

        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
                onCheckedChange(it)
            },
            modifier = Modifier.scale(fontSize.value / 14)
        )
    }
}
