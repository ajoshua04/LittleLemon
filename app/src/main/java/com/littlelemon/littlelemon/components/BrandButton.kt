package com.littlelemon.littlelemon.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.littlelemon.littlelemon.ui.theme.BrandColors
import com.littlelemon.littlelemon.ui.theme.BrandTypography


@Composable
fun BrandButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = BrandColors.PrimaryVariant,
    textColor: Color = Color.Black,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
        ),
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            style = BrandTypography.LeadText,
            color = textColor,
            modifier = Modifier.padding(vertical = 6.dp),
        )
    }
}

