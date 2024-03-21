package com.dojah.sdk_kyc.ui.utils.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.databinding.WidgetDojahButtonBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator.DEF_STYLE_RES
import com.google.android.material.theme.overlay.MaterialThemeOverlay
import okhttp3.logging.HttpLoggingInterceptor
import java.util.logging.Logger

class DojahMaterialButton : MaterialButton {

    private val binding = WidgetDojahButtonBinding.inflate(LayoutInflater.from(context))

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
        MaterialThemeOverlay.wrap(context, attributeSet, R.attr.materialBtnStyle, DEF_STYLE_RES),
        attributeSet,
        R.attr.materialBtnStyle
    ) {
        val brandColor = SharedPreferenceManager(context).getMaterialButtonBgColor
        HttpLoggingInterceptor.Logger.DEFAULT.log("BTN: Brand color: ${brandColor}");
        if (brandColor != null) {
            try {
                setBackgroundColor(Color.parseColor(brandColor))
            } catch (e: Exception) {
                HttpLoggingInterceptor.Logger.DEFAULT.log("${e.message}")
            }
        }
    }

}