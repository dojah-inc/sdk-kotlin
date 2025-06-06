package com.dojah.kyc_sdk_kotlin.ui.utils.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.view.isVisible
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.WidgetDojahAppbarBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.load

class DojahAppBarView : ConstraintLayout {

    private val binding =
        WidgetDojahAppbarBinding.inflate(LayoutInflater.from(context), this)

    var showBackButton: Boolean = true
        set(value) {
            field = value
            binding.backButton.isVisible = value
            if (!value) {
                binding.logo.layoutParams = binding.logo.layoutParams.apply {
                    (this as RelativeLayout.LayoutParams).apply {
                        addRule(
                            RelativeLayout.CENTER_HORIZONTAL,
                            0
                        )
                        addRule(
                            RelativeLayout.CENTER_IN_PARENT,
                            0
                        )
                        addRule(
                            RelativeLayout.ALIGN_PARENT_START,
                            RelativeLayout.TRUE
                        )

                    }
                }
            } else {
                binding.logo.layoutParams = binding.logo.layoutParams.apply {
                    (this as RelativeLayout.LayoutParams).apply {
                        addRule(
                            RelativeLayout.ALIGN_PARENT_START,
                            0
                        )
                        addRule(
                            RelativeLayout.CENTER_IN_PARENT,
                            0
                        )
                        addRule(
                            RelativeLayout.CENTER_HORIZONTAL,
                            RelativeLayout.TRUE
                        )
                    }

                }
            }
//            invalidate()
        }


    var backView: View = binding.backButton

    var closeView: View = binding.trailButton

    var logoUrl: String? = null
        set(value) {
            if (!value.isNullOrEmpty()) {
                binding.logo.load(value, isCenterInside = true)
            }
        }


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
        context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attributeSet, defStyleAttr, defStyleRes) {

        context.obtainStyledAttributes(
            attributeSet,
            R.styleable.DojahAppBarView,
            defStyleAttr,
            defStyleRes
        ).use {
            showBackButton = it.getBoolean(R.styleable.DojahAppBarView_dojahShowBackButton, true)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }


}