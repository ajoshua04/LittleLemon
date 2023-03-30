package com.littlelemon.littlelemon.screens

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.littlelemon.littlelemon.MainActivity
import com.littlelemon.littlelemon.R
import com.littlelemon.littlelemon.components.BrandButton
import com.littlelemon.littlelemon.navigation.Destinations
import com.littlelemon.littlelemon.ui.theme.BrandColors
import com.littlelemon.littlelemon.ui.theme.BrandTypography

@Composable
fun ProfileScreen(navController: NavHostController, sharedPreferences: SharedPreferences) {

    val firstName = sharedPreferences.getString(MainActivity.FIRST_NAME_KEY, "")
    val lastName = sharedPreferences.getString(MainActivity.LAST_NAME_KEY, "")
    val emailAddress = sharedPreferences.getString(MainActivity.EMAIL_ADDRESS_KEY, "")

    val orderStatues = remember { mutableStateOf(false) }
    val passwordChanges = remember { mutableStateOf(false) }
    val specialOffers = remember { mutableStateOf(false) }
    val newsletter = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title =  {
                    Text(text = "Profile", style = BrandTypography.ParagraphText)
                },
                backgroundColor = BrandColors.SurfaceColor,
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Column(modifier = Modifier.weight(0.9f, true)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.placeholder_image),
                            contentDescription = "Person",
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(75.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.FillHeight,
                        )
                        Column {
                            Text(text = "$firstName $lastName".uppercase(), style = BrandTypography.SectionTitle)
                            Text(text = "$emailAddress", style = BrandTypography.ParagraphText)
                        }
                    }

                    Column(modifier = Modifier.padding(vertical = 12.dp)) {
                        Text(
                            text = "Email Notifications",
                            style = BrandTypography.LeadText,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        BrandCheckbox("Order Statuses", orderStatues.value) {
                            orderStatues.value = it
                        }
                        BrandCheckbox("Password Changes", passwordChanges.value) {
                            passwordChanges.value = it
                        }
                        BrandCheckbox("Special Offers", specialOffers.value) {
                            specialOffers.value = it
                        }
                        BrandCheckbox("Newsletter", newsletter.value) {
                            newsletter.value = it
                        }
                    }
                }

                Row(
                    modifier = Modifier.weight(0.1f)
                ) {
                    BrandButton(
                        modifier = Modifier
                            .weight(0.5f, true)
                            .padding(end = 2.dp),
                        text = "Logout",
                        backgroundColor = BrandColors.Primary,
                        textColor = Color.White
                    ) {
                        sharedPreferences.edit().clear().apply()
                        navController.navigate(Destinations.Onboarding.route) {
                            popUpTo(Destinations.Onboarding.route) {
                                inclusive = true
                            }
                        }
                    }

                    BrandButton(
                        modifier = Modifier
                            .weight(0.5f, true)
                            .padding(start = 2.dp),
                        text = "Edit",
                    ) {
                        navController.navigate(Destinations.EditProfile.route)
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BrandCheckbox(label: String, status: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            Checkbox(
                checked = status,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = BrandColors.PrimaryVariant
                ),
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        Text(text = label, style = BrandTypography.ParagraphText)
    }
}