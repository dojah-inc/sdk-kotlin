package com.dojah.sdk_kyc.ui.utils

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.request.target.ImageViewTarget
import com.dojah.sdk_kyc.R

class UserPicTarget(imageView: ImageView,
                    @ColorInt private val tint: Int,
                    private val errorIconSize: Int) : ImageViewTarget<Drawable>(imageView) {

    override fun setResource(resource: Drawable?) {
        view.apply {
            setImageDrawable(resource)
            imageTintList = null
        }
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        view.apply {
            setImageResource(R.drawable.layer_user_pic)
            imageTintList = ColorStateList.valueOf(tint)
            updateLayoutParams<ViewGroup.LayoutParams> {
                width = errorIconSize
                height = errorIconSize
            }
        }
    }

    override fun onLoadStarted(placeholder: Drawable?) {
        super.onLoadStarted(placeholder)
        view.apply {
            setImageResource(R.drawable.layer_user_pic)
            imageTintList = ColorStateList.valueOf(tint)
            updateLayoutParams<ViewGroup.LayoutParams> {
                width = errorIconSize
                height = errorIconSize
            }
        }
    }
}