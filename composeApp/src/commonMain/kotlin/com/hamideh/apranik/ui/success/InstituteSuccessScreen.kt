package com.hamideh.apranik.ui.success

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.unit.sp
import com.hamideh.apranik.ui.theme.EtherlyColors
import com.hamideh.apranik.ui.theme.EtherlyDimensions
import com.hamideh.apranik.ui.theme.EtherlyTypography
import com.hamideh.apranik.ui.util.ArrowIcon
import com.hamideh.apranik.ui.util.BrandMark
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Stateful entry point for the Institute Success screen.
 */
@Composable
fun InstituteSuccessScreen(
    viewModel: InstituteSuccessViewModel,
    logoBytes: ByteArray? = null,
    onCreateCourseClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    InstituteSuccessContent(
        uiState = uiState,
        logoBytes = logoBytes,
        onCreateCourseClick = onCreateCourseClick,
        onSkipClick = onSkipClick
    )
}

/**
 * Stateless content for the Institute Success screen.
 */
@Composable
fun InstituteSuccessContent(
    uiState: InstituteSuccessUiState,
    logoBytes: ByteArray? = null,
    onCreateCourseClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Scaffold(
        topBar = {
            SuccessTopBar(uiState.instituteName, uiState.subdomain, logoBytes)
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

            // Success Illustration
            SuccessIllustration()

            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingXXXLarge))

            // Success Message
            Text(
                text = "You're all set 🎉",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = EtherlyTypography.Headline,
                color = EtherlyColors.HeadlineColor,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingXXLarge))

            // Onboarding Checklist
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(EtherlyDimensions.SpacingLarge)
            ) {
                ChecklistItem("Institute created", isCompleted = true)
                ChecklistItem("Create your first course", isCompleted = false)
                ChecklistItem("Invite your team", isCompleted = false)
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingXXXLarge))

            // Action Buttons
            Button(
                onClick = onCreateCourseClick,
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
                        text = "Create Your First Course",
                        fontSize = EtherlyTypography.ButtonText,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(EtherlyDimensions.SpacingLarge))
                    ArrowIcon(color = Color.White)
                }
            }

            TextButton(
                onClick = onSkipClick,
                modifier = Modifier.padding(vertical = EtherlyDimensions.PaddingSmall)
            ) {
                Text(
                    text = "Skip for now",
                    color = EtherlyColors.BodyColor,
                    fontSize = EtherlyTypography.Body
                )
            }

            Spacer(modifier = Modifier.height(EtherlyDimensions.PaddingLarge))
        }
    }
}

/**
 * Custom header for the success screen showing institute details and optional logo.
 */
@Composable
fun SuccessTopBar(instituteName: String, subdomain: String, logoBytes: ByteArray?) {
    Column(modifier = Modifier.statusBarsPadding()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(EtherlyDimensions.PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (logoBytes != null) {
                // Display the uploaded logo placeholder
                Box(
                    modifier = Modifier
                        .size(EtherlyDimensions.IconSizeExtraLarge)
                        .background(
                            color = EtherlyColors.PillBackground,
                            shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusSmall)
                        )
                        .clip(RoundedCornerShape(EtherlyDimensions.CornerRadiusSmall)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = instituteName.take(1).uppercase(),
                        color = EtherlyColors.AccentGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                BrandMark()
            }

            Column(modifier = Modifier.padding(start = EtherlyDimensions.SpacingSmall)) {
                Text(
                    text = instituteName,
                    color = EtherlyColors.HeadlineColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = subdomain,
                    color = EtherlyColors.BodyColor,
                    fontSize = 12.sp
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(EtherlyDimensions.DividerThickness)
                .background(EtherlyColors.HeaderDivider)
        )
    }
}

/**
 * Custom Canvas-drawn success illustration.
 */
@Composable
fun SuccessIllustration() {
    Box(
        modifier = Modifier
            .size(120.dp)
            .background(color = EtherlyColors.PillBackground, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(60.dp)) {
            val strokeWidth = 6.dp.toPx()
            
            // Draw Checkmark
            val path = androidx.compose.ui.graphics.Path().apply {
                moveTo(size.width * 0.15f, size.height * 0.5f)
                lineTo(size.width * 0.45f, size.height * 0.8f)
                lineTo(size.width * 0.85f, size.height * 0.2f)
            }
            
            drawPath(
                path = path,
                color = EtherlyColors.AccentGreen,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
    }
}

/**
 * A single row item in the onboarding checklist.
 */
@Composable
fun ChecklistItem(text: String, isCompleted: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    color = if (isCompleted) EtherlyColors.AccentGreen else Color.Transparent,
                    shape = CircleShape
                )
                .then(
                    if (!isCompleted) Modifier.background(
                        color = EtherlyColors.HeaderDivider,
                        shape = CircleShape
                    ) else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(EtherlyDimensions.SpacingLarge))
        Text(
            text = text,
            color = if (isCompleted) EtherlyColors.HeadlineColor else EtherlyColors.BodyColor,
            fontSize = EtherlyTypography.Body,
            fontWeight = if (isCompleted) FontWeight.Medium else FontWeight.Normal
        )
    }
}

/**
 * Preview for the Institute Success screen.
 */
@Preview
@Composable
fun InstituteSuccessPreview() {
    MaterialTheme {
        InstituteSuccessContent(
            uiState = InstituteSuccessUiState(
                instituteName = "Etherly Academy",
                subdomain = "etherly.academy.etherly.app"
            ),
            onCreateCourseClick = {},
            onSkipClick = {}
        )
    }
}
