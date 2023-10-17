package com.rrc.adev3007.pixel_perfect.the_y_app.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrc.adev3007.pixel_perfect.the_y_app.R
import com.rrc.adev3007.pixel_perfect.the_y_app.components.PostItem

data class Post(
    val name: String,
    val username: String,
    val profileImage: Int,
    val time: String,
    val content: String
)
@Composable
fun Home() {
    val tweets = listOf(
        Post("Jimmy Neutron", "@Jimmy", R.drawable.jimmy, "2h ago", "Just launched a new experiment. Stay tuned for the results! \uD83D\uDD2C"),
        Post("James Bond", "@007", R.drawable.james,"5h ago", "Mission accomplished. It's been a busy day in the world of espionage. #SecretAgentLife"),
        Post("Tesla Motors", "@TheRealTesla", R.drawable.tesla,"7h ago", "Electric cars are the future! Join us in the green revolution. \uD83C\uDF3F⚡️"),
        Post("Walter White", "@Heisenberg", R.drawable.walter,"10h ago", "Breaking Bad fans, remember the good old days? #HeisenbergReturns"),
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(tweets.size) {index ->
            PostItem(
                name = tweets[index].name,
                username = tweets[index].username,
                profileImage = tweets[index].profileImage,
                time = tweets[index].time,
                content = tweets[index].content,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = if (index == 0) 16.dp else 10.dp,
                        bottom = 10.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White),
            )
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    Home()
}
