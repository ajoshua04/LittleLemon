package com.littlelemon.littlelemon.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.littlelemon.littlelemon.MenuItemRoom
import com.littlelemon.littlelemon.components.MenuListItem
import com.littlelemon.littlelemon.ui.theme.BrandColors
import com.littlelemon.littlelemon.ui.theme.BrandTypography

@Composable
fun SearchScreen(
    navController: NavHostController,
    menuItems: List<MenuItemRoom>
) {
    val searchQuery = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val filteredMenuItems = if (searchQuery.value.text.isNotBlank()) {
        menuItems.filter { menuItem ->
            val title = menuItem.title.lowercase()
            val searchValue = searchQuery.value.text.lowercase()
            title.contains(searchValue)
        }
    } else {
        menuItems
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title =  {
                    Text(text = "Search", style = BrandTypography.ParagraphText)
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
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Column {
                    TextField(
                        value = searchQuery.value,
                        onValueChange = {
                            searchQuery.value = it
                        },
                        placeholder = {
                            Text(
                                text = "Search menu items...",
                                style = BrandTypography.ParagraphText
                            )
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon"
                            )
                        }
                    )

                    LazyColumn {
                        itemsIndexed(
                            items = filteredMenuItems,
                            itemContent = { index, item ->
                                MenuListItem(item, index, navController)
                            }
                        )
                    }
                }
            }
        }
    )
}