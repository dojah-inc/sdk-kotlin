package com.dojah.kyc_sdk_kotlin.ui.utils.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.bumptech.glide.Glide
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.WidgetItemGroupBinding

class ItemGroup : ConstraintLayout {

    private val binding = WidgetItemGroupBinding.inflate(LayoutInflater.from(context), this)

    private var imageBackgroundAlpha: Float = 0f

    private var icon: Drawable? = null

    val imageIcon = binding.imageIcon

    private var showArrow = true
        set(value) {
            field = value
            binding.imageArrow.isVisible = value
        }

    var title = ""
        set(value) {
            field = value
            binding.textTitle.text = value
        }

    var subtitle = ""
        set(value) {
            field = value
            binding.textSubtitle.apply {
                text = value
                isVisible = value.isNotEmpty()
            }
        }

    private var textColor = -1

    private var iconColor = -1

    private var tintIcon: Boolean? = null

    constructor(context: Context) : this(context, null, 0, 0)

    constructor(context: Context, attributeSet: AttributeSet? = null) : this(context, attributeSet, 0, 0)

    constructor(context: Context, attributeSet: AttributeSet? = null, defAttrStyle: Int) : this(context, attributeSet, defAttrStyle, 0)

    constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int,
                defStyleRes: Int) : super(context, attributeSet, R.attr.itemGroupStyle) {

        context.obtainStyledAttributes(attributeSet, R.styleable.ItemGroup, R.attr.itemGroupStyle, defStyleRes).use {
            imageBackgroundAlpha = it.getFloat(R.styleable.ItemGroup_imageBackgroundAlpha, 0.07f)
            icon = it.getDrawable(R.styleable.ItemGroup_icon)
            showArrow = it.getBoolean(R.styleable.ItemGroup_showArrow, true)
            title = it.getString(R.styleable.ItemGroup_title) ?: ""
            subtitle = it.getString(R.styleable.ItemGroup_subtitle) ?: ""
            textColor = it.getColor(R.styleable.ItemGroup_textColor, -1)
            iconColor = it.getColor(R.styleable.ItemGroup_iconColor, -1)
            if(it.hasValue(R.styleable.ItemGroup_tintIcon)) {
                tintIcon = it.getBoolean(R.styleable.ItemGroup_tintIcon, true)
            }
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding.apply {
            textTitle.text = title
            textTitle.setTextColor(ColorStateList.valueOf(textColor))

            textSubtitle.text = subtitle

            imageIcon.setImageDrawable(icon)

            tintIcon.let {
                if(it == null) {
                    imageIcon.imageTintList = ColorStateList.valueOf(iconColor)
                } else if (it) {
                    imageIcon.imageTintList = ColorStateList.valueOf(iconColor)
                } else {
                    imageIcon.imageTintList = null
                }
            }

            textSubtitle.isVisible = subtitle.isNotEmpty()

            setImageAlpha()

            imageArrow.imageTintList = textTitle.textColors
            imageArrow.isVisible = showArrow
        }
    }

    fun setIconRes(@DrawableRes res: Int) {
        binding.imageIcon.apply {
            Glide.with(this)
                    .load(res)
                    .into(this)
                    .request?.apply {
                        if (!isRunning) begin()
                    }
        }
    }

    fun setRawRes(@RawRes res: Int) {
        binding.imageIcon.apply {
            setPadding(0)

            Glide.with(this)
                    .load(res)
                    .centerCrop()
                    .circleCrop()
                    .into(this)
                    .request?.apply {
                        if (!isRunning) begin()
                    }

            background = null
            imageTintList = null
        }
    }

    fun setImageDrawable(drawable: Drawable?) {
        drawable?.also { drawable ->
            binding.imageIcon.apply {
                setImageDrawable(drawable)

                background = null
                imageTintList = null
            }
        }
    }

    fun setEndIconRes(@DrawableRes drawableRes: Int? = null, @ColorRes colorRes: Int? = null) {
        binding.imageArrow.apply {
            if (drawableRes != null) alpha = 1f

            if (drawableRes != null) setImageResource(drawableRes)

            if (colorRes != null) imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
        }
    }

    private fun setImageAlpha() {
        val tintAlpha = (imageBackgroundAlpha * 255).toInt()

        binding.imageIcon.backgroundTintList = ColorStateList.valueOf(iconColor).withAlpha(tintAlpha)
    }

    fun hideIconView() {
        binding.imageIcon.isVisible = false
    }
}