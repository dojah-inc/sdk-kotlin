package com.dojah.kyc_sdk_kotlin.ui.utils.widget;

import android.content.Context
import android.graphics.Rect
import android.os.Handler
import android.os.SystemClock
import android.text.*
import android.text.method.PasswordTransformationMethod
import android.text.style.UpdateLayout
import android.util.AttributeSet
import android.view.View
import androidx.core.view.doOnAttach
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.ui.utils.widget.TextKeyListener.ACTIVE
import com.dojah.kyc_sdk_kotlin.ui.utils.widget.TextKeyListener.SHOW_PASSWORD
import com.dojah.kyc_sdk_kotlin.ui.utils.widget.TextKeyListener.getPrefs
import java.lang.ref.WeakReference

class TextInputEditText @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : TextInputEditText(context, attrs) {

    init {
        doOnAttach {
            setPasswordTransformationMethod()
        }
    }

    fun setPasswordTransformationMethod() {
        transformationMethod = CrossTransformationMethod
    }

    object CrossTransformationMethod : PasswordTransformationMethod() {

        override fun getTransformation(source: CharSequence, view: View?): CharSequence {
            if (source is Spannable) {

                /*
                 * Remove any references to other views that may still be
                 * attached.  This will happen when you flip the screen
                 * while a password field is showing; there will still
                 * be references to the old EditText in the text.
                 */
                val vr = source.getSpans(0, source.length, ViewReference::class.java)
                for (i in vr.indices) source.removeSpan(vr[i])
                removeVisibleSpans(source)
                source.setSpan(ViewReference(view), 0, 0, Spannable.SPAN_POINT_POINT)
            }
            return PasswordCharSequence(source)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // This callback isn't used.
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s is Spannable) {
                val sp = s
                val vr = sp.getSpans(0, s.length,
                        ViewReference::class.java)
                if (vr.size == 0) return

                /*
                 * There should generally only be one ViewReference in the text,
                 * but make sure to look through all of them if necessary in case
                 * something strange is going on.  (We might still end up with
                 * multiple ViewReferences if someone moves text from one password
                 * field to another.)
                 */
                var v: View? = null
                var i = 0
                while (v == null && i < vr.size) {
                    v = vr[i].get()
                    i++
                }
                if (v == null) {
                    return
                }
                val pref: Int = getPrefs(v.context)
                if (pref and SHOW_PASSWORD !== 0) {
                    if (count > 0) {
                        removeVisibleSpans(sp)
                        if (count == 1) {
                            sp.setSpan(Visible(sp, this), start, start + count,
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }
                    }
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
            // This callback isn't used.
        }

        override fun onFocusChanged(view: View?, sourceText: CharSequence?,
                                    focused: Boolean, direction: Int,
                                    previouslyFocusedRect: Rect?) {
            if (!focused) {
                if (sourceText is Spannable) {
                    removeVisibleSpans(sourceText)
                }
            }
        }

        private fun removeVisibleSpans(sp: Spannable) {
            val old = sp.getSpans(0, sp.length, Visible::class.java)
            for (i in old.indices) {
                sp.removeSpan(old[i])
            }
        }

        private class PasswordCharSequence(private val mSource: CharSequence) : CharSequence, GetChars {
            override val length: Int
                get() = mSource.length

            override fun get(i: Int): Char {
                if (mSource is Spanned) {
                    val sp = mSource
                    var st = sp.getSpanStart(ACTIVE)
                    var en = sp.getSpanEnd(ACTIVE)
                    if (i >= st && i < en) {
                        return mSource[i]
                    }
                    val visible = sp.getSpans(0, sp.length, Visible::class.java)
                    for (a in visible.indices) {
                        if (sp.getSpanStart(visible[a].mTransformer) >= 0) {
                            st = sp.getSpanStart(visible[a])
                            en = sp.getSpanEnd(visible[a])
                            if (i >= st && i < en) {
                                return mSource[i]
                            }
                        }
                    }
                }
                return DOT
            }

            override fun subSequence(start: Int, end: Int): CharSequence {
                val buf = CharArray(end - start)
                getChars(start, end, buf, 0)
                return String(buf)
            }

            override fun toString(): String {
                return subSequence(0, length).toString()
            }

            override fun getChars(start: Int, end: Int, dest: CharArray, off: Int) {
                TextUtils.getChars(mSource, start, end, dest, off)
                var st = -1
                var en = -1
                var nvisible = 0
                var starts: IntArray? = null
                var ends: IntArray? = null
                if (mSource is Spanned) {
                    val sp = mSource
                    st = sp.getSpanStart(ACTIVE)
                    en = sp.getSpanEnd(ACTIVE)
                    val visible = sp.getSpans(0, sp.length, Visible::class.java)
                    nvisible = visible.size
                    starts = IntArray(nvisible)
                    ends = IntArray(nvisible)
                    for (i in 0 until nvisible) {
                        if (sp.getSpanStart(visible[i].mTransformer) >= 0) {
                            starts[i] = sp.getSpanStart(visible[i])
                            ends[i] = sp.getSpanEnd(visible[i])
                        }
                    }
                }
                for (i in start until end) {
                    if (!(i >= st && i < en)) {
                        var visible = false
                        for (a in 0 until nvisible) {
                            if (i >= starts!![a] && i < ends!![a]) {
                                visible = true
                                break
                            }
                        }
                        if (!visible) {
                            dest[i - start + off] = DOT
                        }
                    }
                }
            }
        }

        private class Visible(private val mText: Spannable, val mTransformer: PasswordTransformationMethod) : Handler(), UpdateLayout, Runnable {
            override fun run() {
                mText.removeSpan(this)
            }

            init {
                postAtTime(this, SystemClock.uptimeMillis() + 1500)
            }
        }

        /**
         * Used to stash a reference back to the View in the Editable so we
         * can use it to check the settings.
         */
        private class ViewReference(v: View?) : WeakReference<View?>(v), NoCopySpan

        private var sInstance: PasswordTransformationMethod? = null

        private const val DOT = 'x'
    }
}

fun TextInputLayout.changeTextVisibility() {
    addOnEditTextAttachedListener {
        editText!!.apply {
            //set the initial icon#
            setEndIconDrawable(if (transformationMethod != null) R.drawable.ic_show else R.drawable.ic_hide)

            setEndIconOnClickListener {

                if (this is com.dojah.kyc_sdk_kotlin.ui.utils.widget.TextInputEditText) {
                    val selection = selectionEnd

                    if (transformationMethod == null) {
                        setPasswordTransformationMethod()
                        setEndIconDrawable(R.drawable.ic_show)

                    } else {
                        transformationMethod = null
                        setEndIconDrawable(R.drawable.ic_hide)
                    }

                    setSelection(selection)
                }

            }
        }

        it.clearOnEditTextAttachedListeners()
    }
}