package com.hamideh.apranik.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hamideh.apranik.domain.usecase.GetGreetingUseCase

@Composable
fun AppRoot() {
    val greeting = GetGreetingUseCase().execute()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = greeting,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}