package com.hamideh.apranik

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hamideh.apranik.ui.institute.InstituteScreen
import com.hamideh.apranik.ui.institute.InstituteViewModel
import com.hamideh.apranik.ui.success.InstituteSuccessScreen
import com.hamideh.apranik.ui.success.InstituteSuccessViewModel
import com.hamideh.apranik.ui.theme.EtherlyColors
import com.hamideh.apranik.ui.theme.EtherlyDimensions
import com.hamideh.apranik.ui.theme.EtherlyTypography
import com.hamideh.apranik.ui.util.ArrowIcon
import com.hamideh.apranik.ui.util.HomeTopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Sealed class representing the screens in the app.
 */
sealed class Screen {
    object Home : Screen()
    object CreateInstitute : Screen()
    data class InstituteSuccess(val instituteName: String) : Screen()
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
            when (val screen = currentScreen) {
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
                            // On success, navigate to the Success screen with the name
                            currentScreen = Screen.InstituteSuccess(viewModel.uiState.value.instituteName)
                        }
                    )
                }
                is Screen.InstituteSuccess -> {
                    val successViewModel: InstituteSuccessViewModel = viewModel { InstituteSuccessViewModel() }
                    
                    // Initialize the Success state with the name passed from navigation
                    remember(screen.instituteName) {
                        successViewModel.init(screen.instituteName)
                    }

                    InstituteSuccessScreen(
                        viewModel = successViewModel,
                        onCreateCourseClick = {
                            // Placeholder for course creation
                        },
                        onSkipClick = {
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
                        fontSize = EtherlyTypography.ButtonText,
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
 * Previews for the App and HomeScreen.
 */
@Preview
@Composable
fun AppPreview() {
    App()
}

@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(onCreateInstituteClick = {})
    }
}
