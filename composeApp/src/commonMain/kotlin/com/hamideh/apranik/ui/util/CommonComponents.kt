package com.hamideh.apranik.ui.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import com.hamideh.apranik.ui.theme.EtherlyColors
import com.hamideh.apranik.ui.theme.EtherlyDimensions
import com.hamideh.apranik.ui.theme.EtherlyTypography

/**
 * Shared Top Bar with consistent branding and optional Back support.
 */
@Composable
fun HomeTopBar(onBackClick: (() -> Unit)? = null) {
    Column(modifier = Modifier.statusBarsPadding()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = EtherlyDimensions.PaddingMedium,
                    vertical = EtherlyDimensions.PaddingMedium
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = EtherlyColors.HeadlineColor
                    )
                }
                Spacer(modifier = Modifier.width(EtherlyDimensions.SpacingXSmall))
            }
            
            BrandMark()
            Text(
                text = "Etherly",
                color = EtherlyColors.HeadlineColor,
                fontSize = EtherlyTypography.Title,
                fontWeight = FontWeight.Normal,
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
        Canvas(modifier = Modifier.size(EtherlyDimensions.BrandMarkIconSize)) {
            val stroke = EtherlyDimensions.StrokeMedium.toPx()
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
        val strokeWidth = EtherlyDimensions.StrokeThin.toPx()
        drawCircle(
            color = EtherlyColors.HeadlineColor,
            radius = size.minDimension * 0.18f,
            center = Offset(size.width / 2f, size.height * 0.32f),
            style = Stroke(width = strokeWidth),
        )
        drawArc(
            color = EtherlyColors.HeadlineColor,
            startAngle = 205f,
            sweepAngle = 130f,
            useCenter = false,
            topLeft = Offset(size.width * 0.18f, size.height * 0.38f),
            size = androidx.compose.ui.geometry.Size(size.width * 0.64f, size.height * 0.42f),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
    }
}

/**
 * Draws the menu action icon in the header.
 */
@Composable
fun MenuIcon() {
    Canvas(modifier = Modifier.size(EtherlyDimensions.IconSizeLarge)) {
        val stroke = EtherlyDimensions.StrokeMedium.toPx()
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
 * Draws a lightweight arrow icon.
 */
@Composable
fun ArrowIcon(color: Color) {
    Canvas(modifier = Modifier.size(EtherlyDimensions.IconSizeSmall)) {
        val stroke = EtherlyDimensions.StrokeThin.toPx()
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
