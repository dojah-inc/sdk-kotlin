package com.dojah.sdk_kyc.ui.utils.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.res.use
import androidx.core.view.isVisible
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.WidgetButtonWithProgressIndicatorBinding

class LoadingButton : FrameLayout {

    private val binding = WidgetButtonWithProgressIndicatorBinding.inflate(LayoutInflater.from(context), this)

    var text = ""
        set(value) {
            field = value
            binding.button.text = value
        }

    var isButtonEnabled = false
        set(value) {
            field = value
            binding.button.isEnabled = value
        }

    var isLoading = false
        set(value) {
            field = value

            if (value) showProgressDrawable()
            else hideProgressDrawable()
        }

    constructor(context: Context) : this(context, null, 0, 0)

    constructor(context: Context, attributeSet: AttributeSet? = null) : this(context, attributeSet, 0, 0)

    constructor(context: Context, attributeSet: AttributeSet? = null, defAttrStyle: Int) : this(context, attributeSet, defAttrStyle, 0)

    constructor(context: Context, attributeSet: AttributeSet? = null,
                defStyleAttr: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttr, defStyleRes) {

        context.obtainStyledAttributes(attributeSet, R.styleable.LoadingButton, defStyleAttr, defStyleRes).use {
            text = it.getString(R.styleable.LoadingButton_buttonText) ?: ""
            isButtonEnabled = it.getBoolean(R.styleable.LoadingButton_buttonEnabled, false)
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.button.setOnClickListener(l)
    }

    override fun isEnabled(): Boolean {
        return binding.button.isEnabled
    }

    override fun setEnabled(enabled: Boolean) {
        binding.button.isEnabled = enabled
    }

    private fun showProgressDrawable() {
        binding.apply {
            button.isSelected = true
            button.isEnabled = false
            button.text = null
            progressIndicator.isVisible = true
        }
    }

    private fun hideProgressDrawable() {
        binding.apply {
            button.isSelected = false
            button.isEnabled = true
            button.text = this@LoadingButton.text
            progressIndicator.isVisible = false
        }
    }
}