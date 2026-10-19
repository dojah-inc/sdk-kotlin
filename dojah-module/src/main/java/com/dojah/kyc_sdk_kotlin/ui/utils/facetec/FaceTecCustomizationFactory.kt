package com.dojah.kyc_sdk_kotlin.ui.utils.facetec

import android.graphics.Color
import com.dojah.kyc_sdk_kotlin.ui.utils.normaliseColor
import com.facetec.sdk.FaceTecCustomization
import com.facetec.sdk.FaceTecSecurityWatermarkImage

internal object FaceTecCustomizationFactory {
    private const val DEFAULT_BRAND = "#3F7CDB"
    private const val WHITE = "#FFFFFF"

    fun fromBrandColor(brandColorHex: String?): FaceTecCustomization {
        val brand = parseColor(brandColorHex) ?: parseColor(DEFAULT_BRAND)!!
        val white = Color.parseColor(WHITE)
        val customization = FaceTecCustomization()

        customization.frameCustomization.backgroundColor = white
        customization.frameCustomization.borderColor = brand
        customization.overlayCustomization.backgroundColor = white

        customization.guidanceCustomization.backgroundColors = white
        customization.guidanceCustomization.foregroundColor = brand
        customization.guidanceCustomization.buttonBackgroundNormalColor = brand
        customization.guidanceCustomization.buttonBackgroundHighlightColor = brand
        customization.guidanceCustomization.buttonTextNormalColor = white
        customization.guidanceCustomization.buttonTextHighlightColor = white
        customization.guidanceCustomization.retryScreenOvalStrokeColor = brand

        customization.ovalCustomization.strokeColor = brand
        customization.ovalCustomization.progressColor1 = brand
        customization.ovalCustomization.progressColor2 = brand

        customization.feedbackCustomization.backgroundColors = brand
        customization.feedbackCustomization.textColor = white

        customization.resultScreenCustomization.backgroundColors = white
        customization.resultScreenCustomization.foregroundColor = brand
        customization.resultScreenCustomization.activityIndicatorColor = brand
        customization.resultScreenCustomization.resultAnimationBackgroundColor = brand
        customization.resultScreenCustomization.resultAnimationForegroundColor = white
        customization.resultScreenCustomization.uploadProgressFillColor = brand

        customization.securityWatermarkImage = FaceTecSecurityWatermarkImage.FACETEC
        return customization
    }

    private fun parseColor(raw: String?): Int? {
        if (raw.isNullOrBlank()) return null
        return try {
            Color.parseColor(raw)
        } catch (_: Exception) {
            try {
                raw.normaliseColor()
            } catch (_: Exception) {
                null
            }
        }
    }
}
