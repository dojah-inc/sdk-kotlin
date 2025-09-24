package com.dojah.kyc_sdk_kotlin.utils

import android.graphics.Bitmap

fun Bitmap.rotate(degrees: Float): Bitmap {
    val matrix = android.graphics.Matrix().apply { postRotate(degrees) }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}