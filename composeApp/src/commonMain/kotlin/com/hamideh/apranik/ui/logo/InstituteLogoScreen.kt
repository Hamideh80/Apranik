package com.hamideh.apranik.ui.logo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hamideh.apranik.ui.theme.EtherlyColors
import com.hamideh.apranik.ui.theme.EtherlyDimensions
import com.hamideh.apranik.ui.theme.EtherlyTypography
import com.hamideh.apranik.ui.util.ArrowIcon
import com.hamideh.apranik.ui.util.HomeTopBar
import com.hamideh.apranik.ui.util.rememberBitmapFromBytes
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Stateful entry point for the Optional Institute Logo Upload screen.
 */
@Composable
fun InstituteLogoScreen(
    viewModel: InstituteLogoViewModel,
    onTriggerPicker: () -> Unit,
    onContinue: (ByteArray?) -> Unit,
    onSkip: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    InstituteLogoContent(
        uiState = uiState,
        onTriggerPicker = onTriggerPicker,
        onContinue = { onContinue(uiState.selectedImageBytes) },
        onSkip = onSkip
    )
}

/**
 * Stateless content for the Optional Institute Logo Upload screen.
 */
@Composable
fun InstituteLogoContent(
    uiState: InstituteLogoUiState,
    onTriggerPicker: () -> Unit,
    onContinue: () -> Unit,
    onSkip: () -> Unit
) {
    Scaffold(
        topBar = {
            HomeTopBar()
        },
        containerColor = EtherlyColors.PageBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = EtherlyDimensions.PaddingLarge)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(EtherlyDimensions.PaddingExtraLarge))

            // Header Section
            Text(
                text = "Add Your Institute Logo",
                style = MaterialTheme.typography.headlineMedium,
                color = EtherlyColors.HeadlineColor,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingSmall))
            Text(
                text = "This will appear on your platform. You can also do this later.",
                style = MaterialTheme.typography.bodyMedium,
                color = EtherlyColors.BodyColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = EtherlyDimensions.PaddingMedium)
            )

            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingXXXXLarge))

            // Logo Picker Section
            val bitmap = rememberBitmapFromBytes(uiState.selectedImageBytes)
            
            Box(
                modifier = Modifier
                    .size(EtherlyDimensions.LogoPickerSize)
                    .background(
                        color = EtherlyColors.PillBackground,
                        shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusMedium)
                    )
                    .clip(RoundedCornerShape(EtherlyDimensions.CornerRadiusMedium))
                    .clickable { onTriggerPicker() },
                contentAlignment = Alignment.Center
            ) {
                if (bitmap == null) {
                    AddIcon()
                } else {
                    Image(
                        bitmap = bitmap,
                        contentDescription = "Selected Logo",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingLarge))
            
            Text(
                text = "Upload JPG or PNG (max 1MB)",
                style = MaterialTheme.typography.bodySmall,
                color = EtherlyColors.BodyColor,
                textAlign = TextAlign.Center
            )

            if (uiState.errorMessage != null) {
                Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingSmall))
                Text(
                    text = uiState.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingXXXLarge))

            // Action Buttons
            Button(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(EtherlyDimensions.ButtonHeight),
                shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusSmall),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EtherlyColors.AccentGreen
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Continue",
                        style = MaterialTheme.typography.labelLarge,
                    )
                    Spacer(modifier = Modifier.width(EtherlyDimensions.SpacingLarge))
                    ArrowIcon(color = Color.White)
                }
            }

            TextButton(
                onClick = onSkip,
                modifier = Modifier.padding(vertical = EtherlyDimensions.PaddingSmall)
            ) {
                Text(
                    text = "Skip for now",
                    color = EtherlyColors.BodyColor,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(EtherlyDimensions.PaddingLarge))
        }
    }
}

/**
 * Custom Canvas-drawn plus (+) icon for the logo picker.
 */
@Composable
fun AddIcon() {
    Canvas(modifier = Modifier.size(EtherlyDimensions.IconSizeExtraLarge)) {
        val strokeWidth = EtherlyDimensions.StrokeThick.toPx()
        val color = EtherlyColors.AccentGreen
        
        // Vertical line
        drawLine(
            color = color,
            start = androidx.compose.ui.geometry.Offset(size.width / 2, size.height * 0.2f),
            end = androidx.compose.ui.geometry.Offset(size.width / 2, size.height * 0.8f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        
        // Horizontal line
        drawLine(
            color = color,
            start = androidx.compose.ui.geometry.Offset(size.width * 0.2f, size.height / 2),
            end = androidx.compose.ui.geometry.Offset(size.width * 0.8f, size.height / 2),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

/**
 * Preview for the Institute Logo screen.
 */
@Preview
@Composable
fun InstituteLogoPreview() {
    MaterialTheme {
        InstituteLogoContent(
            uiState = InstituteLogoUiState(),
            onTriggerPicker = {},
            onContinue = {},
            onSkip = {}
        )
    }
}
