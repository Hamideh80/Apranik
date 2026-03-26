package com.hamideh.apranik.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

/**
 * Custom Material 3 Theme for Etherly.
 */
@Composable
fun EtherlyTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme(
        primary = EtherlyColors.AccentGreen,
        background = EtherlyColors.PageBackground,
        surface = EtherlyColors.PageBackground,
        onPrimary = EtherlyColors.White,
        onBackground = EtherlyColors.HeadlineColor,
        onSurface = EtherlyColors.HeadlineColor
    )

    val typography = Typography(
        headlineMedium = TextStyle(
            fontSize = EtherlyTypography.Headline,
            lineHeight = EtherlyTypography.HeadlineLineHeight,
            fontWeight = FontWeight.SemiBold
        ),
        titleLarge = TextStyle(
            fontSize = EtherlyTypography.Title,
            fontWeight = FontWeight.Normal
        ),
        bodyMedium = TextStyle(
            fontSize = EtherlyTypography.Body,
            lineHeight = EtherlyTypography.BodyLineHeight
        ),
        labelLarge = TextStyle(
            fontSize = EtherlyTypography.ButtonText,
            fontWeight = FontWeight.Bold
        )
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}
