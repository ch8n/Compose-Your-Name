package io.github.ch8n.whatis.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import whatis.R

val NunitoSansFontFamily = FontFamily(
    Font(R.font.nunitosans_bold, FontWeight.Bold),
    Font(R.font.nunitosans_light, FontWeight.Light),
    Font(R.font.nunitosans_semibold, FontWeight.SemiBold)
)


val Typography = Typography(
    h1 = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    caption = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    )
)
