package com.dojah.sdk_kyc.ui.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.toSpannable
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.dojah.sdk_kyc.BuildConfig
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager


/**
 * Add the prefix text to the returned string
 */
fun TextInputLayout.getText(
    withPrefix: Boolean = false,
    withoutWhitespace: Boolean = false
): String {
    return (buildString {
        if (withPrefix && prefixText != null) append(prefixText)

        append(editText?.text?.toString() ?: "")

    }).run {
        if (withoutWhitespace) replace("\\s+".toRegex(), "")
        else this
    }.trim()
}

fun TextInputLayout.setText(text: CharSequence?) {
    editText?.setText(text)
}

fun TextInputLayout.isValidEmail(): Boolean {
    return getText().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(getText()).matches()
}

fun View.hideSoftKeyboard(window: Window) {
    WindowCompat.getInsetsController(window, this)
        .hide(WindowInsetsCompat.Type.ime())
}

fun TextView.setClickableText(
    startIndex: Int,
    endIndex: Int,
    color: Int? = null,
    onClick: () -> Unit
) {
    val span = text.toSpannable().apply {
        setSpan(object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                SharedPreferenceManager(context).getMaterialButtonBgColor.let {
                    if (it == null) {
                        color?.let { newColor -> ds.color = newColor }
                    } else {
                        ds.color = Color.parseColor(it)
                    }
                }
                ds.isUnderlineText = false
                ds.textSize = 40f
            }


            override fun onClick(p0: View) {
                onClick.invoke()
            }

        }, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    text = span
    if (movementMethod != LinkMovementMethod.getInstance()) {
        movementMethod = LinkMovementMethod.getInstance()
    }
}

fun TextInputLayout.watchAndFormatAmount() {
    var current = ""

    val textChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            val string = text.toString()

            if (string != current && string.isNotEmpty()) {
                editText?.removeTextChangedListener(this)

                current = string.convertBalanceToNumber().formatToBalance()
                setText(current)
                editText?.setSelection(current.length)

                editText?.addTextChangedListener(this)
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    addOnEditTextAttachedListener {
        editText?.addTextChangedListener(textChangeListener)
        it.clearOnEditTextAttachedListeners()
    }
}

fun TextInputLayout.disallowEditing() {
    addOnEditTextAttachedListener {
        editText?.apply {
            inputType = InputType.TYPE_NULL
            isEnabled = false
        }

        clearOnEditTextAttachedListeners()
    }
}

fun TextInputLayout.allowEditing(input: Int) {
    addOnEditTextAttachedListener {
        editText?.apply {
            isEnabled = true
            inputType = input
        }
        clearOnEditTextAttachedListeners()
    }
}

fun TextInputLayout.watchAndFormatPhoneNumber(region: String = "NG") {
//    var hasBeenModified = false
//    val phoneUtil = PhoneNumberUtil.getInstance()
//    val formatter = phoneUtil.getAsYouTypeFormatter(region)
//
//    addOnEditTextAttachedListener {
//        editText!!.doOnTextChanged { text, start, before, count ->
//            val string = text.toString()
//            val inputtedText = string.substring(start, start + count)
//
//            if(string.isEmpty()) formatter.clear()
//
//            if (hasBeenModified) hasBeenModified = false
//            else {
//                if (inputtedText.containsADigitChar()) {
//                    hasBeenModified = true
//
//                    val asChars = inputtedText.substring(0, string.length-1).map { it }
//                    val lastChar = inputtedText.substring(string.length-1, string.length)[0]
//
//                    asChars.forEach { formatter.inputDigit(it) }
//                    val number = formatter.inputDigit(lastChar)
//                    setText(number)
//                    editText!!.setSelection(number.length)
//                }
//            }
//            it.clearOnEditTextAttachedListeners()
//        }
//    }
}

fun TextInputLayout.watchAndFormatAccountNumber() {
//    var hasBeenModified = false
//
//    addOnEditTextAttachedListener {
//        editText!!.doOnTextChanged { text, _, _, _ ->
//
//            if (hasBeenModified) hasBeenModified = false
//            else {
//                hasBeenModified = true
//
//                val string = text?.toString() ?: ""
//
//                StringBuilder(string).apply {
//                    if (string.length > 8) set(8, ' ')
//
//                    if (string.length > 4) set(4, ' ')
//
//                    val formatted = toString()
//
//                    setText(formatted)
//                    editText!!.setSelection(formatted.length)
//                }
//            }
//        }
//
//        it.clearOnEditTextAttachedListeners()
//    }
}

//Consider returning a listener which will be added to the click listener
//of each view since the touch listener is too sensitive
fun handleSingleSelectionForGroupedViews(vararg views: View) {
    views.forEach {
        it.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                views.filter { view: View -> view.id != it.id }.forEach { other: View ->
                    other.isSelected = false
                }
            }

            v.performClick()
        }
    }
}

fun ImageView.load(
    any: Any?,
    isCenterCrop: Boolean = false,
    isCircleCrop: Boolean = false,
    isCenterInside: Boolean = false
) {
    Glide.with(this)
        .load(any)
        .let {
            if (isCenterCrop) it.centerCrop()
            if (isCenterInside) it.centerInside()
            if (isCircleCrop) it.circleCrop()

            it.into(this)
        }
        .request
        ?.apply { if (!isRunning) begin() }
}

fun ImageView.loadGif(
    any: Any?,
    isCenterCrop: Boolean = false,
    isCircleCrop: Boolean = false,
    isCenterInside: Boolean = false
) {
    Glide.with(this)
        .asGif()
        .load(any)
        .let {
            if (isCenterCrop) it.centerCrop()
            if (isCenterInside) it.centerInside()
            if (isCircleCrop) it.circleCrop()

            it.into(this)
        }
        .request
        ?.apply { if (!isRunning) begin() }
}

fun MaterialButton.load(url: String) {
    Glide.with(this)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                ContextCompat.getMainExecutor(context).execute {
                    this@load.icon = resource
                }

                return true
            }

        })
        .submit()
        .request?.apply {
            if (!isRunning) begin()
        }
}

fun Context.makeDebugToast(message: String) {
    if (BuildConfig.DEBUG) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}