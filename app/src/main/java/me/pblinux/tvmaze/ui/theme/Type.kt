package me.pblinux.tvmaze.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.pblinux.tvmaze.R

val montserrat = FontFamily(
    Font(R.font.montserrat_thin, weight = FontWeight.Thin),
    Font(R.font.montserrat_extra_light, weight = FontWeight.ExtraLight),
    Font(R.font.montserrat_light, weight = FontWeight.Light),
    Font(R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(R.font.montserrat_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(R.font.montserrat_extra_bold, weight = FontWeight.ExtraBold),
    Font(R.font.montserrat_black, weight = FontWeight.Black),
)

val LightTypography = Typography(
    h1 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Light,
        fontSize = 97.sp,
        color = OxfordBlue
    ),
    h2 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Light,
        fontSize = 61.sp,
        color = OxfordBlue
    ),
    h3 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        color = OxfordBlue
    ),
    h4 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        color = OxfordBlue
    ),
    h5 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        color = OxfordBlue
    ),
    h6 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        color = OxfordBlue
    ),
    subtitle1 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = OxfordBlue
    ),
    subtitle2 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = OxfordBlue
    ),
    body1 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = OxfordBlue
    ),
    button = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = OxfordBlue
    ),
    caption = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = OxfordBlue
    ),
    overline = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        color = OxfordBlue
    ),
)

val DarkTypography = Typography(
    h1 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Light,
        fontSize = 97.sp,
        color = White
    ),
    h2 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Light,
        fontSize = 61.sp,
        color = White
    ),
    h3 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        color = White
    ),
    h4 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        color = White
    ),
    h5 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        color = White
    ),
    h6 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        color = White
    ),
    subtitle1 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = White
    ),
    subtitle2 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = White
    ),
    body1 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = White
    ),
    body2 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = White
    ),
    button = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = White
    ),
    caption = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = White
    ),
    overline = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        color = White
    ),
)