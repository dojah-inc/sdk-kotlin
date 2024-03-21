package com.dojah.sdk_kyc.ui.utils.widget

import android.R.color
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.google.android.material.progressindicator.CircularProgressIndicator.DEF_STYLE_RES
import com.google.android.material.theme.overlay.MaterialThemeOverlay
import com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap
import okhttp3.logging.HttpLoggingInterceptor


class DojahTextView : AppCompatTextView {
    constructor(context: Context) : this(context, null, 0, 0)

    constructor(context: Context, attributeSet: AttributeSet? = null) : this(
        context,
        attributeSet,
        0,
        0
    )

    constructor(context: Context, attributeSet: AttributeSet? = null, defAttrStyle: Int) : this(
        context,
        attributeSet,
        defAttrStyle,
        0
    )

    constructor(
        context: Context, attributeSet: AttributeSet? = null,
        defStyleAttr: Int, defStyleRes: Int
    ) : super(
        wrap(context, attributeSet, defStyleAttr, defStyleRes),
        attributeSet,
        defStyleAttr
    ) {
        val brandColor = SharedPreferenceManager(context).getMaterialButtonBgColor
        HttpLoggingInterceptor.Logger.DEFAULT.log("TXT: Brand color: $brandColor");
        if (brandColor != null) {
            for (drawable in compoundDrawables) {
                if (drawable != null) {
                    try {
                        drawable.colorFilter = PorterDuffColorFilter(
                            Color.parseColor(brandColor),
                            PorterDuff.Mode.SRC_IN
                        )
                    } catch (e: Exception) {
                        HttpLoggingInterceptor.Logger.DEFAULT.log("${e.message}")
                    }
                }
            }
            for (drawable in compoundDrawablesRelative) {
                if (drawable != null) {
                    try {
                        drawable.colorFilter = PorterDuffColorFilter(
                            Color.parseColor(brandColor),
                            PorterDuff.Mode.SRC_IN
                        )
                    } catch (e: Exception) {
                        HttpLoggingInterceptor.Logger.DEFAULT.log("${e.message}")
                    }
                }
            }
        }
    }

}