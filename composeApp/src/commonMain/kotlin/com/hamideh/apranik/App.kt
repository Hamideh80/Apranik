package com.hamideh.apranik

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hamideh.apranik.ui.institute.InstituteScreen
import com.hamideh.apranik.ui.institute.InstituteViewModel
import com.hamideh.apranik.ui.theme.EtherlyColors
import com.hamideh.apranik.ui.theme.EtherlyDimensions
import com.hamideh.apranik.ui.theme.EtherlyTypography

/**
 * Sealed class representing the screens in the app.
 */
sealed class Screen {
    object Home : Screen()
    object CreateInstitute : Screen()
}

/**
 * Renders the shared application root used by Android and iOS.
 */
@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = EtherlyColors.PageBackground,
        ) {
            when (currentScreen) {
                is Screen.Home -> {
                    HomeScreen(
                        onCreateInstituteClick = {
                            currentScreen = Screen.CreateInstitute
                        }
                    )
                }
                is Screen.CreateInstitute -> {
                    val viewModel: InstituteViewModel = viewModel { InstituteViewModel() }
                    InstituteScreen(
                        viewModel = viewModel,
                        onBackClick = {
                            currentScreen = Screen.Home
                        },
                        onInstituteCreated = {
                            // After success, we could navigate back to home or a dashboard
                            currentScreen = Screen.Home
                        }
                    )
                }
            }
        }
    }
}

/**
 * Displays the first landing page with navigation to Institute creation.
 */
@Composable
fun HomeScreen(onCreateInstituteClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EtherlyColors.PageBackground)
            .statusBarsPadding(),
    ) {
        HomeTopBar()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EtherlyDimensions.PaddingMedium)
                .padding(top = EtherlyDimensions.PaddingExtraLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Learn at Your Own Pace",
                color = EtherlyColors.HeadlineColor,
                fontSize = EtherlyTypography.Badge,
                fontWeight = EtherlyTypography.WeightMedium,
                modifier = Modifier
                    .background(
                        color = EtherlyColors.PillBackground,
                        shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusMedium),
                    )
                    .padding(
                        horizontal = EtherlyDimensions.PaddingLarge,
                        vertical = EtherlyDimensions.PaddingSmall
                    ),
            )
            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingXXXLarge))
            Text(
                text = "Learning for Everyone,\nEverywhere",
                color = EtherlyColors.HeadlineColor,
                fontSize = EtherlyTypography.Headline,
                lineHeight = EtherlyTypography.HeadlineLineHeight,
                fontWeight = EtherlyTypography.WeightSemiBold,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingXXLarge))
            Text(
                text = "Etherly is a calm, reliable learning platform designed for students and lifelong learners. Access courses online or offline, from school to philosophy.",
                color = EtherlyColors.BodyColor,
                fontSize = EtherlyTypography.Body,
                lineHeight = EtherlyTypography.BodyLineHeight,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = EtherlyDimensions.PaddingMedium),
            )
            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingXXXLarge))
            
            // Primary Action: Navigate to Create Institute
            Button(
                onClick = onCreateInstituteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(EtherlyDimensions.ButtonHeight),
                shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusSmall),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EtherlyColors.AccentGreen,
                    contentColor = Color.White,
                ),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Create Your Institute",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.size(EtherlyDimensions.SpacingLarge))
                    ArrowIcon(color = Color.White)
                }
            }
        }
    }
}

/**
 * Shows the header row with brand and utility actions.
 */
@Composable
fun HomeTopBar() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = EtherlyDimensions.PaddingMedium,
                    vertical = EtherlyDimensions.PaddingMedium
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BrandMark()
            Text(
                text = "Etherly",
                color = EtherlyColors.HeadlineColor,
                fontSize = EtherlyTypography.Title,
                fontWeight = EtherlyTypography.WeightNormal,
                modifier = Modifier.padding(start = EtherlyDimensions.SpacingSmall),
            )
            Spacer(modifier = Modifier.weight(1f))
            AccountIcon()
            Spacer(modifier = Modifier.size(EtherlyDimensions.SpacingXLarge))
            MenuIcon()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(EtherlyDimensions.DividerThickness)
                .background(EtherlyColors.HeaderDivider),
        )
    }
}

/**
 * Draws the brand icon displayed in the header.
 */
@Composable
fun BrandMark() {
    Box(
        modifier = Modifier
            .size(EtherlyDimensions.IconSizeExtraLarge)
            .background(
                color = EtherlyColors.AccentGreen,
                shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusSmall)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(EtherlyDimensions.IconSizeMedium)) {
            val stroke = 2.dp.toPx()
            drawLine(
                color = Color.White,
                start = Offset(size.width * 0.30f, size.height * 0.30f),
                end = Offset(size.width * 0.30f, size.height * 0.70f),
                strokeWidth = stroke,
                cap = StrokeCap.Round,
            )
            drawLine(
                color = Color.White,
                start = Offset(size.width * 0.70f, size.height * 0.30f),
                end = Offset(size.width * 0.70f, size.height * 0.70f),
                strokeWidth = stroke,
                cap = StrokeCap.Round,
            )
            drawLine(
                color = Color.White,
                start = Offset(size.width * 0.30f, size.height * 0.30f),
                end = Offset(size.width * 0.70f, size.height * 0.30f),
                strokeWidth = stroke,
                cap = StrokeCap.Round,
            )
            drawLine(
                color = Color.White,
                start = Offset(size.width * 0.30f, size.height * 0.50f),
                end = Offset(size.width * 0.70f, size.height * 0.50f),
                strokeWidth = stroke,
                cap = StrokeCap.Round,
            )
        }
    }
}

/**
 * Draws the account action icon in the header.
 */
@Composable
fun AccountIcon() {
    Canvas(modifier = Modifier.size(EtherlyDimensions.IconSizeLarge)) {
        val strokeWidth = 1.8.dp.toPx()
        drawCircle(
            color = EtherlyColors.HeadlineColor,
            radius = size.minDimension * 0.18f,
            center = Offset(size.width / 2f, size.height * 0.32f),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth),
        )
        drawArc(
            color = EtherlyColors.HeadlineColor,
            startAngle = 205f,
            sweepAngle = 130f,
            useCenter = false,
            topLeft = Offset(size.width * 0.18f, size.height * 0.38f),
            size = androidx.compose.ui.geometry.Size(size.width * 0.64f, size.height * 0.42f),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
    }
}

/**
 * Draws the menu action icon in the header.
 */
@Composable
fun MenuIcon() {
    Canvas(modifier = Modifier.size(EtherlyDimensions.IconSizeLarge)) {
        val stroke = 2.dp.toPx()
        val startX = size.width * 0.18f
        val endX = size.width * 0.82f
        drawLine(
            color = EtherlyColors.HeadlineColor,
            start = Offset(startX, size.height * 0.28f),
            end = Offset(endX, size.height * 0.28f),
            strokeWidth = stroke,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = EtherlyColors.HeadlineColor,
            start = Offset(startX, size.height * 0.50f),
            end = Offset(endX, size.height * 0.50f),
            strokeWidth = stroke,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = EtherlyColors.HeadlineColor,
            start = Offset(startX, size.height * 0.72f),
            end = Offset(endX, size.height * 0.72f),
            strokeWidth = stroke,
            cap = StrokeCap.Round,
        )
    }
}

/**
 * Draws a lightweight arrow icon for the primary action.
 */
@Composable
fun ArrowIcon(color: Color) {
    Canvas(modifier = Modifier.size(EtherlyDimensions.IconSizeSmall)) {
        val stroke = 1.8.dp.toPx()
        drawLine(
            color = color,
            start = Offset(size.width * 0.1f, size.height * 0.5f),
            end = Offset(size.width * 0.85f, size.height * 0.5f),
            strokeWidth = stroke,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.55f, size.height * 0.22f),
            end = Offset(size.width * 0.85f, size.height * 0.5f),
            strokeWidth = stroke,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.55f, size.height * 0.78f),
            end = Offset(size.width * 0.85f, size.height * 0.5f),
            strokeWidth = stroke,
            cap = StrokeCap.Round,
        )
    }
}
