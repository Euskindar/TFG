package com.i72pehej.cpuschedulerapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val darkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200

    primary = Azul_com_1,
    primaryVariant = Azul_base,
    secondary = Azul_com_4,
    secondaryVariant = Azul_com_2,
)

private val lightColorPalette = lightColors(
//    primary = Purple500,
//    primaryVariant = Purple700,
//    secondary = Teal200

    primary = Azul_base,
    primaryVariant = Azul_com_5,
    secondary = Azul_com_2,
    secondaryVariant = Azul_com_4,

    /* Other default colors to override
    onPrimary = Color.White,
    onSecondary = Color.Black,
    */
)

@Composable
fun CpuSchedulerAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}