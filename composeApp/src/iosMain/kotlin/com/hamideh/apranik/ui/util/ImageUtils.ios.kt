package com.hamideh.apranik.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

/**
 * iOS implementation for converting ByteArray to ImageBitmap using Skia.
 */
@Composable
actual fun rememberBitmapFromBytes(bytes: ByteArray?): ImageBitmap? {
    return remember(bytes) {
        if (bytes != null) {
            Image.makeFromEncoded(bytes).toComposeImageBitmap()
        } else {
            null
        }
    }
}
