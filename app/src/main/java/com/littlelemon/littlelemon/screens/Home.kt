package com.littlelemon.littlelemon.screens

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.littlelemon.littlelemon.*
import com.littlelemon.littlelemon.R
import com.littlelemon.littlelemon.components.MenuListItem
import com.littlelemon.littlelemon.navigation.Destinations
import com.littlelemon.littlelemon.ui.theme.BrandColors
import com.littlelemon.littlelemon.ui.theme.BrandTypography
import java.util.*

@Composable
fun HomeScreen(
    navController: NavHostController,
    data: List<MenuItemRoom>,
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val onChangeSearch: (TextFieldValue) -> (Unit) = {
        searchText = it
    }

    val selectedCategory: MutableState<Categories?> = remember {
        mutableStateOf(null)
    }
    var categoryFilterText by remember { mutableStateOf("") }
    val menuItems = if (selectedCategory.value != null) {
        data.filter { menuItem ->
            val categoryToEnum = Categories.valueOf(menuItem.category.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            })
            categoryToEnum == selectedCategory.value
        }
    } else {
        data.filter{
            it.title.contains(
                searchText.text,
                ignoreCase = true
            )
        }.filter {
            it.category.contains(
                categoryFilterText,
                ignoreCase = true
            )
        }.sortedBy {
            it.title
        }
    }

    Column {
        HomeNavbar(navController = navController)
        UpperPanelHome(searchText, onChangeSearch)
        MenuBreakDown( selectedCategory = selectedCategory)

        LazyColumn {
            itemsIndexed(
                items = menuItems,
                itemContent = { index, item ->
                    MenuListItem(item, index, navController)
                }
            )
        }
    }
}

@Composable
fun HomeNavbar(navController: NavController) {
    val context = LocalContext.current
    

    Box(
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        Image(
            painter = painterResource(id = R.drawable.littlelemontopbar),
            contentDescription = "Logo",
            modifier = Modifier
                .width(200.dp)
                .height(80.dp)
                .align(Alignment.Center)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile",
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                .clickable {
                    navController.navigate(Destinations.Profile.route)
                }
                .align(Alignment.CenterEnd),
        )
    }
}

@Composable
fun UpperPanelHome(searchText: TextFieldValue, onChangeSearch: (TextFieldValue) -> (Unit)){

    Column(
        modifier = Modifier
            .background(BrandColors.Primary)
            .padding(horizontal = 16.dp, vertical = 18.dp)
    ) {
        Text(
            text = "Little Lemon",
            style = BrandTypography.DisplayTitle,
            color = BrandColors.PrimaryVariant,
        )
        Row {
            Column(
                modifier = Modifier.weight(0.6f, true),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Chicago",
                    style = BrandTypography.Subtitle,
                    color = Color.White,
                    modifier = Modifier.absoluteOffset(y = (-26).dp)
                )

                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    style = BrandTypography.ParagraphText,
                    color = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .height(130.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        TextField(value = searchText,
            placeholder = { Text("Enter Search Phrase") },
            singleLine = true,
            onValueChange = { onChangeSearch(it) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFFFFFFF),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .clip(
                    shape = RoundedCornerShape(10.dp)
                )
        )
    }
}

@Composable
fun MenuBreakDown(selectedCategory: MutableState<Categories?>){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 18.dp)
    ) {
        Text(
            text = "Order for Delivery".uppercase(),
            style = BrandTypography.SectionTitle
        )
        LazyRow(
            modifier = Modifier.padding(top = 12.dp)
        ) {
            items(Categories.values()) { category ->
                var selectedBackground = BrandColors.SurfaceColor
                var selectedTextColor = Color.Black
                if (selectedCategory.value == category) {
                    selectedBackground = Color(0xFF333333)
                    selectedTextColor = Color.White
                }
                Button(
                    onClick = {
                        selectedCategory.value = if (selectedCategory.value == category) {
                            null
                        } else {
                            category
                        }
                    },
                    modifier = Modifier
                        .padding(end = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = selectedBackground,
                    ),
                    shape = RoundedCornerShape(100),
                    elevation = null,
                ) {
                    Text(
                        text = category.name,
                        style = BrandTypography.SectionCategories,
                        color = selectedTextColor
                    )
                }
            }
        }
    }
}

@Composable
fun ItemsColumn(database: AppDatabase, searchText: TextFieldValue, categoryFilterText: String){

}