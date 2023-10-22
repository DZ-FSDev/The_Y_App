package com.rrc.adev3007.pixel_perfect.the_y_app.components

import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit


@Composable
fun SetAsMediaButton(
    text: String,
    onImageSelected: (String) -> Unit, 
    fontSize: TextUnit,
    color: Color
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imageUri ->
            val contentResolver = context.contentResolver
            val inputStream = contentResolver.openInputStream(imageUri)
            val byteArray = inputStream?.readBytes()
            val base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT)
            onImageSelected(base64Image)
            inputStream?.close()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                launcher.launch("image/*")
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
    }
}
