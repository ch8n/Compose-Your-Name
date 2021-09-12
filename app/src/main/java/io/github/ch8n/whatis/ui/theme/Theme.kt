package io.github.ch8n.whatis.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorPalette = lightColors(
    primary = Orange,
    primaryVariant = Black,
    secondary = Salmon,
    surface = OffWhite,
    secondaryVariant = OffWhite,
    onPrimary = OffWhite,
    onError = Color.Red,
    background = OffWhite,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black,
)

@Composable
fun WhatisTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}