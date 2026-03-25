package com.hamideh.apranik.ui.institute

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamideh.apranik.ui.theme.EtherlyColors
import com.hamideh.apranik.ui.theme.EtherlyDimensions
import com.hamideh.apranik.ui.theme.EtherlyTypography
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Stateful entry point for the Institute creation screen.
 */
@Composable
fun InstituteScreen(
    viewModel: InstituteViewModel,
    onBackClick: () -> Unit,
    onInstituteCreated: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    InstituteScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onBackClick = onBackClick,
        onInstituteCreated = onInstituteCreated
    )
}

/**
 * Stateless content Composable, ideal for Previews and testing.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstituteScreenContent(
    uiState: InstituteUiState,
    onEvent: (InstituteUiEvent) -> Unit,
    onBackClick: () -> Unit,
    onInstituteCreated: () -> Unit
) {
    // Handle navigation on success
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onInstituteCreated()
        }
    }

    Scaffold(
        topBar = {
            HomeTopBar(onBackClick = onBackClick)
        },
        containerColor = EtherlyColors.PageBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = EtherlyDimensions.PaddingLarge)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(EtherlyDimensions.SpacingXXLarge)
        ) {
            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingMedium))

            // Header Section
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Create Your Institute",
                    style = MaterialTheme.typography.headlineMedium,
                    color = EtherlyColors.HeadlineColor,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingSmall))
                Text(
                    text = "Set up your learning space in minutes",
                    style = MaterialTheme.typography.bodyMedium,
                    color = EtherlyColors.BodyColor,
                    textAlign = TextAlign.Center
                )
            }

            // Form Fields
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(EtherlyDimensions.SpacingLarge)
            ) {
                OutlinedTextField(
                    value = uiState.instituteName,
                    onValueChange = { onEvent(InstituteUiEvent.OnInstituteNameChanged(it)) },
                    label = { Text("Institute Name") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusSmall),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EtherlyColors.AccentGreen,
                        unfocusedBorderColor = EtherlyColors.SecondaryBorder,
                        focusedLabelColor = EtherlyColors.AccentGreen,
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )

                OutlinedTextField(
                    value = uiState.adminName,
                    onValueChange = { onEvent(InstituteUiEvent.OnAdminNameChanged(it)) },
                    label = { Text("Admin Name") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusSmall),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EtherlyColors.AccentGreen,
                        unfocusedBorderColor = EtherlyColors.SecondaryBorder,
                        focusedLabelColor = EtherlyColors.AccentGreen,
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )

                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { onEvent(InstituteUiEvent.OnEmailChanged(it)) },
                    label = { Text("Email Address") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusSmall),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EtherlyColors.AccentGreen,
                        unfocusedBorderColor = EtherlyColors.SecondaryBorder,
                        focusedLabelColor = EtherlyColors.AccentGreen,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { onEvent(InstituteUiEvent.OnPasswordChanged(it)) },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusSmall),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EtherlyColors.AccentGreen,
                        unfocusedBorderColor = EtherlyColors.SecondaryBorder,
                        focusedLabelColor = EtherlyColors.AccentGreen,
                    ),
                    visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { onEvent(InstituteUiEvent.OnTogglePasswordVisibility) }) {
                            if (uiState.isPasswordVisible) {
                                Icon(
                                    imageVector = Icons.Default.VisibilityOff,
                                    contentDescription = "Hide password",
                                    tint = EtherlyColors.BodyColor
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = "Show password",
                                    tint = EtherlyColors.BodyColor
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true
                )
            }

            // CTA Button
            Button(
                onClick = { onEvent(InstituteUiEvent.OnSubmit) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(EtherlyDimensions.ButtonHeight),
                enabled = uiState.isFormValid && !uiState.isLoading,
                shape = RoundedCornerShape(EtherlyDimensions.CornerRadiusSmall),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EtherlyColors.AccentGreen,
                    disabledContainerColor = EtherlyColors.AccentGreen.copy(alpha = 0.5f)
                )
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Create Institute",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            if (uiState.errorMessage != null) {
                Text(
                    text = uiState.errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            
            Spacer(modifier = Modifier.height(EtherlyDimensions.SpacingXXLarge))
        }
    }
}

/**
 * Shared Top Bar with consistent branding and Back support.
 */
@Composable
fun HomeTopBar(onBackClick: () -> Unit = {}) {
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
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = EtherlyColors.HeadlineColor
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
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
 * Preview for the Institute creation screen.
 */
@Preview
@Composable
fun InstituteScreenPreview() {
    MaterialTheme {
        InstituteScreenContent(
            uiState = InstituteUiState(
                instituteName = "Etherly Academy",
                adminName = "John Doe",
                email = "admin@etherly.com"
            ),
            onEvent = {},
            onBackClick = {},
            onInstituteCreated = {}
        )
    }
}
