package com.littlelemon.littlelemon.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.littlelemon.littlelemon.MenuItemRoom
import com.littlelemon.littlelemon.MenuViewModel
import com.littlelemon.littlelemon.ui.theme.BrandColors
import com.littlelemon.littlelemon.ui.theme.BrandTypography
import java.util.*

@Composable
fun ItemDetailsScreen(itemId: Int?, viewModel: MenuViewModel, navController: NavHostController) {

    val menuItem: MutableState<MenuItemRoom?> = remember {
        mutableStateOf(null)
    }

    LaunchedEffect(Unit) {
        if (itemId != null) {
            menuItem.value =  viewModel.fetchMenuItem(itemId)
        }
    }

    if (menuItem.value == null) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        ItemDetailComponent(menuItem.value!!, navController)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemDetailComponent(menuItem: MenuItemRoom, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title =  {
                    Text(text = menuItem.title, style = BrandTypography.ParagraphText)
                },
                backgroundColor = BrandColors.SurfaceColor,
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Arrow")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Column(Modifier.fillMaxSize()) {
                GlideImage(
                    model = menuItem.image,
                    contentDescription = menuItem.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = menuItem.title,
                        style = BrandTypography.Subtitle,
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Text(
                            text = menuItem.category.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    locale = Locale.getDefault()
                                ) else it.toString()
                            },
                            style = BrandTypography.SectionCategories,
                            color = Color.LightGray
                        )
                        Text(
                            text = "$${menuItem.price}.00",
                            style = BrandTypography.HighlightText,
                            color = Color.Black
                        )
                    }
                    Text(text = menuItem.description, style = BrandTypography.ParagraphText)
                }
            }
        }
    }
}