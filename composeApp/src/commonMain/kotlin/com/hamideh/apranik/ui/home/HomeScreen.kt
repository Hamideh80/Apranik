package com.hamideh.apranik.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.hamideh.apranik.ui.theme.EtherlyColors
import com.hamideh.apranik.ui.theme.EtherlyDimensions
import com.hamideh.apranik.ui.theme.EtherlyTypography
import com.hamideh.apranik.ui.util.ArrowIcon
import com.hamideh.apranik.ui.util.HomeTopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

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
                style = MaterialTheme.typography.bodySmall, // Example of using theme style
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
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingXXLarge))
            Text(
                text = "Etherly is a calm, reliable learning platform designed for students and lifelong learners. Access courses online or offline, from school to philosophy.",
                color = EtherlyColors.BodyColor,
                style = MaterialTheme.typography.bodyMedium,
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
                        style = MaterialTheme.typography.labelLarge,
                    )
                    Spacer(modifier = Modifier.size(EtherlyDimensions.SpacingLarge))
                    ArrowIcon(color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(onCreateInstituteClick = {})
    }
}
