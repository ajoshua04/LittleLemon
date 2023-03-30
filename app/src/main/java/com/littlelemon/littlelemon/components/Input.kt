package com.littlelemon.littlelemon.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.littlelemon.littlelemon.ui.theme.BrandColors
import com.littlelemon.littlelemon.ui.theme.BrandTypography

@Composable
fun Input(
    label: String,
    inputValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    errorMessage: String = "",
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp),
            style = BrandTypography.HighlightText
        )
        TextField(
            value = inputValue,
            onValueChange = onValueChange,
            singleLine = true,
            isError = isError,
            textStyle = BrandTypography.ParagraphText,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BrandColors.SurfaceColor,
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = keyboardType
            ),
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val borderSize = 1.dp.toPx()
                    drawLine(
                        color = Color(0xFF495E57),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderSize
                    )
                },
            trailingIcon = {
                if (isError) {
                    Icon(
                        imageVector = Icons.Rounded.Info,
                        contentDescription = "Error", tint =
                        MaterialTheme.colors.error
                    )
                }
            }
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = BrandTypography.ParagraphText,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}