package com.littlelemon.littlelemon.ui.theme

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.littlelemon.littlelemon.R

val MarkaziTextFamily = FontFamily(
    Font(R.font.markazi_text_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.markazi_text_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.markazi_text_medium, FontWeight.Medium,  FontStyle.Normal),
    Font(R.font.markazi_text_semi_bold, FontWeight.SemiBold,  FontStyle.Normal)
)

val KarlaFamily = FontFamily(
    Font(R.font.karla_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.karla_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.karla_extra_bold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.karla_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.karla_extra_light, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.karla_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.karla_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.karla_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.karla_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.karla_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.karla_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.karla_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.karla_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.karla_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
)

object BrandTypography {
    val Subtitle = TextStyle(
        fontFamily = MarkaziTextFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    )

    val DisplayTitle = TextStyle(
        fontFamily = MarkaziTextFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 64.sp,
    )

    val SectionTitle = TextStyle(
        fontFamily = KarlaFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    )

    val HighlightText = TextStyle(
        fontFamily = KarlaFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )

    val LeadText = TextStyle(
        fontFamily = KarlaFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    )

    val ParagraphText = TextStyle(
        fontFamily = KarlaFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    val SectionCategories = TextStyle(
        fontFamily = KarlaFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    )

    val CardTitle = TextStyle(
        fontFamily = KarlaFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}

