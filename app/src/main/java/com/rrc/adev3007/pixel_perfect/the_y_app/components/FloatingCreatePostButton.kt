package com.rrc.adev3007.pixel_perfect.the_y_app.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rrc.adev3007.pixel_perfect.the_y_app.data.viewModels.PostViewModel
import com.rrc.adev3007.pixel_perfect.the_y_app.session.SessionViewModel

@ExperimentalComposeUiApi
@Composable
fun FloatingCreatePostButton(sessionViewModel: SessionViewModel, postViewModel: PostViewModel) {
    NewPostForm(sessionViewModel, postViewModel)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 15.dp, bottom = 85.dp)
    ) {
        androidx.compose.material3.FloatingActionButton(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .align(Alignment.BottomEnd),
            containerColor = Color.Blue,
            onClick = { NewPostFormState.toggleForm() },
        ) {
            Text(
                color = Color.White,
                fontSize = 30.sp,
                text = "+",
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}
