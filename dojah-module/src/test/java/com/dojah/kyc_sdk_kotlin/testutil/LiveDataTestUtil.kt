package com.dojah.kyc_sdk_kotlin.testutil

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.dojah.kyc_sdk_kotlin.core.Result
import org.robolectric.Shadows
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

object ResultTestUtil {
    fun isSuccess(value: Any?): Boolean = value is Result.Success<*>

    fun isError(value: Any?): Boolean = value is Result.Error

    fun isApiError(value: Any?): Boolean = value is Result.Error.ApiError
}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 3,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {},
    predicate: (T) -> Boolean = { true },
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = Observer<T> { value ->
        if (predicate(value)) {
            data = value
            latch.countDown()
        }
    }
    observeForever(observer)
    try {
        afterObserve()
        val deadlineNanos = System.nanoTime() + timeUnit.toNanos(time)
        while (!latch.await(50, TimeUnit.MILLISECONDS)) {
            idleMainLooper()
            if (System.nanoTime() >= deadlineNanos) {
                throw TimeoutException("LiveData value matching predicate was never set.")
            }
        }
    } finally {
        removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

fun idleMainLooper() {
    try {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
    } catch (_: RuntimeException) {
        // Robolectric is not active in pure JVM tests.
    }
}
