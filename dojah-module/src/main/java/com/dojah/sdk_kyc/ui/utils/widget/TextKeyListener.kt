/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dojah.sdk_kyc.ui.utils.widget

import android.content.ContentResolver
import android.content.Context
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.text.*
import android.text.NoCopySpan.Concrete
import android.text.method.BaseKeyListener
import java.lang.ref.WeakReference

object TextKeyListener : BaseKeyListener(), SpanWatcher {
    private var mPrefs = 0
    private var mPrefsInited = false
    private var mResolver: WeakReference<ContentResolver?>? = null
    private var mObserver: SettingsObserver? = null

    override fun getInputType(): Int {

//        return makeTextContentType(mAutoCap, mAutoText)
        return InputType.TYPE_NUMBER_VARIATION_PASSWORD
    }

    override fun onSpanAdded(s: Spannable, what: Any, start: Int, end: Int) {}
    override fun onSpanRemoved(s: Spannable, what: Any, start: Int, end: Int) {}
    override fun onSpanChanged(s: Spannable, what: Any, start: Int, end: Int,
                               st: Int, en: Int) {
        if (what === Selection.SELECTION_END) {
            s.removeSpan(ACTIVE)
        }
    }

    enum class Capitalize {
        NONE, SENTENCES, WORDS, CHARACTERS
    }

    fun release() {
        if (mResolver != null) {
            val contentResolver = mResolver!!.get()
            if (contentResolver != null) {
                contentResolver.unregisterContentObserver(mObserver!!)
                mResolver!!.clear()
            }
            mObserver = null
            mResolver = null
            mPrefsInited = false
        }
    }

    private fun initPrefs(context: Context) {
        val contentResolver = context.contentResolver
        mResolver = WeakReference(contentResolver)
        if (mObserver == null) {
            mObserver = SettingsObserver()
            contentResolver.registerContentObserver(Settings.System.CONTENT_URI, true, mObserver!!)
        }
        updatePrefs(contentResolver)
        mPrefsInited = true
    }

    private fun updatePrefs(resolver: ContentResolver) {
        val cap = Settings.System.getInt(resolver, Settings.System.TEXT_AUTO_CAPS, 1) > 0
        val text = Settings.System.getInt(resolver, Settings.System.TEXT_AUTO_REPLACE, 1) > 0
        val period = Settings.System.getInt(resolver, Settings.System.TEXT_AUTO_PUNCTUATE, 1) > 0
        val pw = Settings.System.getInt(resolver, Settings.System.TEXT_SHOW_PASSWORD, 1) > 0
        mPrefs = (if (cap) AUTO_CAP else 0) or
                (if (text) AUTO_TEXT else 0) or
                (if (period) AUTO_PERIOD else 0) or
                if (pw) SHOW_PASSWORD else 0
    }

    fun getPrefs(context: Context): Int {
        synchronized(this) {
            if (!mPrefsInited || mResolver!!.get() == null) {
                initPrefs(context)
            }
        }
        return mPrefs
    }


    /* package */
    val ACTIVE: Any = Concrete()

    /* package */
    val CAPPED: Any = Concrete()

    /* package */
    val INHIBIT_REPLACEMENT: Any = Concrete()

    /* package */
    val LAST_TYPED: Any = Concrete()

    /* package */
    const val AUTO_CAP = 1

    /* package */
    const val AUTO_TEXT = 2

    /* package */
    const val AUTO_PERIOD = 4

    /* package */
    const val SHOW_PASSWORD = 8

    /**
     * Returns whether it makes sense to automatically capitalize at the
     * specified position in the specified text, with the specified rules.
     *
     * @param cap the capitalization rules to consider.
     * @param cs the text in which an insertion is being made.
     * @param off the offset into that text where the insertion is being made.
     *
     * @return whether the character being inserted should be capitalized.
     */
    fun shouldCap(cap: Capitalize, cs: CharSequence, off: Int): Boolean {
        var i: Int
        var c: Char
        if (cap == Capitalize.NONE) {
            return false
        }
        return if (cap == Capitalize.CHARACTERS) {
            true
        } else TextUtils.getCapsMode(cs, off, if (cap == Capitalize.WORDS) TextUtils.CAP_MODE_WORDS else TextUtils.CAP_MODE_SENTENCES) != 0
    }

    /**
     * Clear all the input state (autotext, autocap, multitap, undo)
     * from the specified Editable, going beyond Editable.clear(), which
     * just clears the text but not the input state.
     *
     * @param e the buffer whose text and state are to be cleared.
     */
    fun clear(e: Editable) {
        e.clear()
        e.removeSpan(ACTIVE)
        e.removeSpan(CAPPED)
        e.removeSpan(INHIBIT_REPLACEMENT)
        e.removeSpan(LAST_TYPED)
        val repl: Array<Replaced> = e.getSpans(0, e.length,
                Replaced::class.java)
        val count = repl.size
        for (i in 0 until count) {
            e.removeSpan(repl[i])
        }
    }

    internal class Replaced(private val mText: CharArray) : NoCopySpan

    class SettingsObserver() : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            if (mResolver != null) {
                val contentResolver = mResolver!!.get()
                if (contentResolver == null) {
                    mPrefsInited = false
                } else {
                    updatePrefs(contentResolver)
                }
            } else {
                mPrefsInited = false
            }
        }
    }
}