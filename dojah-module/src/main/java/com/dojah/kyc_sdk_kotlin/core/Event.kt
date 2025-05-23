package com.dojah.kyc_sdk_kotlin.core

import java.util.concurrent.atomic.AtomicBoolean

class Event<out T>(private val content: T) {

    private var _atomicHasBeenHandled = AtomicBoolean(false)

    val hasBeenHandled = _atomicHasBeenHandled.get()

    fun getContentIfNotHandled(): T?{
        return if(_atomicHasBeenHandled.get()) null
        else {
            _atomicHasBeenHandled.set(true)
            content
        }
    }

    fun peekContent(): T = content
}