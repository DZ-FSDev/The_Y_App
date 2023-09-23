package com.rrc.adev3007.pixel_perfect.the_y_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rrc.adev3007.pixel_perfect.the_y_app.R

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    currentRoute: String? = "Home"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                iconRes = R.drawable.home,
                onClick = {
                    navController.navigate("Home")
                },
                isSelected = currentRoute == "Home",
                contentDescription = currentRoute,
            )
            BottomNavItem(
                iconRes = R.drawable.search,
                onClick = {
                    navController.navigate("Search")
                },
                isSelected = currentRoute == "Search",
                contentDescription = currentRoute,
            )
            BottomNavItem(
                iconRes = R.drawable.thumbs_down,
                onClick = {
                    navController.navigate("Dislikes")
                },
                isSelected = currentRoute == "Dislikes",
                contentDescription = currentRoute,
            )
        }
    }
}

@Composable
fun BottomNavItem(
    iconRes: Int,
    onClick: () -> Unit,
    isSelected: Boolean,
    contentDescription: String?,
) {
    val iconPainter = painterResource(id = iconRes)
    val tint = if (isSelected) Color.White else Color.Gray

    IconToggleButton(
        checked = isSelected,
        onCheckedChange = { if (!isSelected) onClick() },
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = "$contentDescription Icon",
            tint = tint
        )
    }
}
