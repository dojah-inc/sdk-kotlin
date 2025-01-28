package com.dojah_inc.dojah_android_sdk.ui.utils.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
import com.dojah_inc.dojah_android_sdk.databinding.WidgetDojahButtonBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator.DEF_STYLE_RES
import com.google.android.material.theme.overlay.MaterialThemeOverlay
import okhttp3.logging.HttpLoggingInterceptor

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
        try {
            setBackgroundColor(getBrandButtonColor(context))
        } catch (e: Exception) {
            HttpLoggingInterceptor.Logger.DEFAULT.log("${e.message}")
        }
    }

    fun getBrandButtonColor(context: Context): Int {
        val color = SharedPreferenceManager(context).getMaterialButtonBgColor
        if (color != null) {
            return Color.parseColor(color)
        }
        return ContextCompat.getColor(context, R.color.brand)
    }


}