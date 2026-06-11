package com.dojah.kyc_sdk_kotlin.utils

import android.graphics.Bitmap

import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

fun Bitmap.rotate(degrees: Float): Bitmap {
    val matrix = android.graphics.Matrix().apply { postRotate(degrees) }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

/**
 * Convert a Bitmap to a Base64 string.
 * - format: PNG/JPEG/WEBP
 * - quality: for JPEG/WEBP (0..100), ignored for PNG
 * Uses Base64.NO_WRAP to avoid newline chars.
 */
fun Bitmap.toBase64(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 100
): String {
    val stream = ByteArrayOutputStream()
    this.compress(format, quality, stream)
    val bytes = stream.toByteArray()
    stream.close()
    return Base64.encodeToString(bytes, Base64.NO_WRAP)
}

/**
 * Resize the bitmap if it exceeds given max dimension while preserving aspect ratio.
 * If no resize necessary returns the same bitmap.
 */
fun Bitmap.scaleToMax(maxWidth: Int, maxHeight: Int): Bitmap {
    val w = this.width
    val h = this.height
    if (w <= maxWidth && h <= maxHeight) return this

    val ratio = minOf(maxWidth.toFloat() / w, maxHeight.toFloat() / h)
    val nw = (w * ratio).toInt()
    val nh = (h * ratio).toInt()
    return Bitmap.createScaledBitmap(this, nw, nh, true)
}

/** Decode Base64 string back to Bitmap (returns null if decode fails) */
fun String.base64ToBitmap(): Bitmap? {
    return try {
        val bytes = Base64.decode(this, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    } catch (e: Exception) {
        null
    }
}