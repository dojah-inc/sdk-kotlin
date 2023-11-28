package com.dojah.sdk_kyc.ui.utils

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Flow
import androidx.core.text.trimmedLength
import androidx.databinding.BindingAdapter
import com.dojah.sdk_kyc.R

@BindingAdapter("bind:backgroundAlpha")
fun backgroundAlpha(view: View, alpha: Double) {
    val tintAlpha = alpha * 255

    view.backgroundTintList?.let {
        view.backgroundTintList = it.withAlpha(tintAlpha.toInt())
    }
}

@BindingAdapter("bind:groupEms")
fun groupEms(parent: View, yes: Boolean) {
    if (!yes) return

//    parent.findViewById<Flow>(R.id.flow_money)?.let { flow ->
//        val textViews = flow.referencedIds.map {
//            parent.findViewById<TextView>(it)
//        }
//
//        val maxLength = textViews.maxOf { textView -> textView.text.trimmedLength() }
//
//        textViews.forEach {
//            it.setEms(maxLength)
//        }
//    }
}