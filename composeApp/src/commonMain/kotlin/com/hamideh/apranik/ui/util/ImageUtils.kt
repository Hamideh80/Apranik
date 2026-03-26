package com.hamideh.apranik.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

/**
 * Platform-specific decoder to convert ByteArray to ImageBitmap.
 */
@Composable
expect fun rememberBitmapFromBytes(bytes: ByteArray?): ImageBitmap?
