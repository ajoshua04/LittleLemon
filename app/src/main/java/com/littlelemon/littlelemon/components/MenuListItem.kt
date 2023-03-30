package com.littlelemon.littlelemon.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.littlelemon.littlelemon.MenuItemRoom
import com.littlelemon.littlelemon.navigation.Destinations
import com.littlelemon.littlelemon.ui.theme.BrandColors
import com.littlelemon.littlelemon.ui.theme.BrandTypography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuListItem(
    item: MenuItemRoom,
    index: Int,
    navController: NavController
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navController.navigate(Destinations.ItemDetails.route + "/${item.id}")
            }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(0.8f, true)
                .height(90.dp)
                .padding(end = 12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Column {
                Text(
                    text = item.title,
                    style = BrandTypography.CardTitle
                )
                Text(
                    text = item.description,
                    style = BrandTypography.ParagraphText,
                    maxLines = 2,
                )
            }
            Text(
                text = "$${item.price}",
                style = BrandTypography.HighlightText
            )
        }
        GlideImage(
            model = item.image,
            contentDescription = "Hero Image",
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
        )
    }
    if (index < 10) {
        Divider(
            color = BrandColors.SurfaceColor,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}