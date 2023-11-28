package com.dojah.sdk_kyc.ui.utils.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.WidgetSimpleAppbarBinding

class SimpleAppBarView : ConstraintLayout {

    private val binding =
        WidgetSimpleAppbarBinding.inflate(LayoutInflater.from(context), this)
    var title = ""
        set(value) {
            field = value
            binding.textTitle.text = value
        }
    var leadingDrawable: Drawable? = null
        set(value) {
            field = value
            binding.backButton.setImageDrawable(value)
        }
    var trailingDrawable: Drawable? = null
        set(value) {
            field = value
            binding.trailButton.setImageDrawable(value)
        }

    val leadingView: AppCompatImageButton = binding.backButton

    fun setLeadingView(value: View) {
        binding.apply {
            // remove the leadingButton from the layout
            root.removeViewAt(0)
            // add the new leadingButton to the layout
            root.addView(value, 0)
            invalidate()
        }
    }

    val trailView: AppCompatImageButton = binding.trailButton

    fun setTrailView(value: View) {
        binding.apply {
            // remove the leadingButton from the layout
            root.removeViewAt(2)
            // add the new leadingButton to the layout
            root.addView(value, 2)
            invalidate()
        }
    }

    private var leadingResId: Int = -1
    private var trailingResId: Int = -1


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
            R.styleable.SimpleAppBarView,
            defStyleAttr,
            defStyleRes
        ).use {
            title = it.getString(R.styleable.SimpleAppBarView_appBarTitle) ?: ""
            leadingDrawable = it.getDrawable(R.styleable.SimpleAppBarView_leadingIcon)
            trailingDrawable = it.getDrawable(R.styleable.SimpleAppBarView_trailingIcon)
            leadingResId = it.getResourceId(R.styleable.SimpleAppBarView_leadingLayout, -1)
            trailingResId = it.getResourceId(R.styleable.SimpleAppBarView_trailingLayout, -1)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        val leadView = getViewById(leadingResId)
        val trailView = getViewById(trailingResId)
        if (leadView != null) {
            setLeadingView(leadView)
        }
        if (trailView != null) {
            setTrailView(trailView)
        }
    }

}