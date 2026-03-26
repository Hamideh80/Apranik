package com.hamideh.apranik

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hamideh.apranik.ui.home.HomeScreen
import com.hamideh.apranik.ui.institute.InstituteScreen
import com.hamideh.apranik.ui.institute.InstituteViewModel
import com.hamideh.apranik.ui.logo.InstituteLogoScreen
import com.hamideh.apranik.ui.logo.InstituteLogoViewModel
import com.hamideh.apranik.ui.success.InstituteSuccessScreen
import com.hamideh.apranik.ui.success.InstituteSuccessViewModel
import com.hamideh.apranik.ui.theme.EtherlyTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Sealed class representing the screens in the app.
 */
sealed class Screen {
    object Home : Screen()
    object CreateInstitute : Screen()
    data class AddLogo(val instituteName: String) : Screen()
    data class InstituteSuccess(val instituteName: String, val logoBytes: ByteArray? = null) : Screen()
}

/**
 * Renders the shared application root used by Android and iOS.
 */
@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    EtherlyTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
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
                            currentScreen = Screen.AddLogo(viewModel.uiState.value.instituteName)
                        }
                    )
                }
                is Screen.AddLogo -> {
                    val logoViewModel: InstituteLogoViewModel = viewModel { InstituteLogoViewModel() }
                    InstituteLogoScreen(
                        viewModel = logoViewModel,
                        onTriggerPicker = {
                            // Logic to trigger platform picker would go here
                        },
                        onContinue = { bytes ->
                            currentScreen = Screen.InstituteSuccess(screen.instituteName, bytes)
                        },
                        onSkip = {
                            currentScreen = Screen.InstituteSuccess(screen.instituteName, null)
                        }
                    )
                }
                is Screen.InstituteSuccess -> {
                    val successViewModel: InstituteSuccessViewModel = viewModel { InstituteSuccessViewModel() }
                    
                    remember(screen.instituteName) {
                        successViewModel.init(screen.instituteName)
                    }

                    InstituteSuccessScreen(
                        viewModel = successViewModel,
                        logoBytes = screen.logoBytes,
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
 * Previews for the full App flow.
 */
@Preview
@Composable
fun AppPreview() {
    App()
}
