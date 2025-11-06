package com.dojah.kyc_sdk_kotlin.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CancellableCountDownTimer(
    private val totalSeconds: Long,
    private val onTick: (Long) -> Unit = {},
    private val onFinish: () -> Unit = {},
    private val onStart: () -> Unit = {},
) {
    private var job: Job? = null

    fun start(totalSeconds: Long = this.totalSeconds) {
        if (job != null) return

        job = CoroutineScope(Dispatchers.Main).launch {
            onStart()
            for (i in totalSeconds downTo 0) {
                onTick(i)
                delay(1000L) // Wait for 1 second
            }
            onFinish()
        }
    }

    fun stop() {
        job?.cancel()
        job = null
    }
}
