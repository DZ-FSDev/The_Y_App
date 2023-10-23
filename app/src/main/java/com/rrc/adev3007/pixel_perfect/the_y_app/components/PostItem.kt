package com.rrc.adev3007.pixel_perfect.the_y_app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rrc.adev3007.pixel_perfect.the_y_app.R

@Composable
fun PostItem(
    name: String,
    username: String,
    profileImage: String?,
    time: String,
    content: String,
    modifier: Modifier
) {
    Box(
        modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProfileIcon(
                drawableRes = R.drawable.person,
                iconSize = 38.dp,
                modifier = Modifier
            )
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$username · $time",
                        color = Color.White,
                        fontSize = 11.sp
                    )
                }

                Text(
                    text = content,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPost() {
    PostItem(
        name = "Sample Post",
        username = "SampleUser",
        profileImage = null,
        time = "2h",
        content = "This is the post content",
        modifier = Modifier
    )
}