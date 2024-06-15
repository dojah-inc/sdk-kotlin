package com.dojah_inc.dojah_android_sdk.ui.utils.widget.otp_view

import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.util.AttributeSet

internal class OTPChildEditText : androidx.appcompat.widget.AppCompatEditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        isCursorVisible = false
        setTextColor(Color.TRANSPARENT)
        background = null
        inputType = InputType.TYPE_CLASS_NUMBER
        setSelectAllOnFocus(false)
        setTextIsSelectable(false)
    }

    public override fun onSelectionChanged(start: Int, end: Int) {

        val text = text
        text?.let {
            if (start != it.length || end != it.length) {
                setSelection(it.length, it.length)
                return
            }
        }

        super.onSelectionChanged(start, end)
    }

}