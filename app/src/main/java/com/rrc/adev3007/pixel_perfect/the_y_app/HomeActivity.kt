package com.rrc.adev3007.pixel_perfect.the_y_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rrc.adev3007.pixel_perfect.the_y_app.components.BottomNavBar
import com.rrc.adev3007.pixel_perfect.the_y_app.components.DefaultProfileIcon
import com.rrc.adev3007.pixel_perfect.the_y_app.components.Drawer
import com.rrc.adev3007.pixel_perfect.the_y_app.components.DrawerState
import com.rrc.adev3007.pixel_perfect.the_y_app.pages.Dislikes
import com.rrc.adev3007.pixel_perfect.the_y_app.pages.Home
import com.rrc.adev3007.pixel_perfect.the_y_app.pages.Search
import com.rrc.adev3007.pixel_perfect.the_y_app.ui.theme.LogoFontFamily
import com.rrc.adev3007.pixel_perfect.the_y_app.session.SessionViewModel

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = SessionViewModel(applicationContext)
        setContent {
            HomeScreen(viewModel)
        }
    }

    @Composable
    fun HomeScreen(viewModel: SessionViewModel) {
        val darkMode by viewModel.darkMode
        val username by viewModel.username
        val profilePicture by viewModel.profilePicture

        val navController = rememberNavController()
        val currentRoute =
            navController.currentBackStackEntryAsState().value?.destination?.route
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if(darkMode) Color.Black
            else Color.White
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    DefaultProfileIcon(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart),
                        onClick = { DrawerState.toggleDrawer() },
                        imageBase64 = profilePicture
                    )
                    Text(
                        text = "y",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = LogoFontFamily,
                        modifier =
                        Modifier
                            .align(Alignment.TopCenter)
                            .clickable {
                                if (currentRoute != "Home")
                                    navController.navigate("Home")
                            }
                    )
                }

                Text(
                    text = "Welcome Back $username",
                    color = if(darkMode) Color.Blue
                    else Color.Yellow,
                    modifier = Modifier.padding(start = 0.dp)
                )

                Spacer(modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.White))
                NavHost(
                    navController,
                    startDestination = "Home",
                    modifier = Modifier.weight(1f)
                ) {
                    composable("Home") { Home() }
                    composable("Search") { Search() }
                    composable("Dislikes") { Dislikes() }
                }
                BottomNavBar(
                    navController = navController,
                    currentRoute = currentRoute,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
            }
            Drawer(viewModel)
        }
    }
}
