package com.littlelemon.littlelemon.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Onboarding: Destinations(route = "Onboarding")
    object Home: Destinations(
        route = "Home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile: Destinations(
        route = "Profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object ItemDetails: Destinations(route = "ItemDetails") {
        const val itemId = "ItemId"
    }
    object EditProfile: Destinations(route = "EditProfile")
}