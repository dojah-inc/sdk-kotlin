package com.dojah.kyc_sdk_kotlin.core.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CancellableCountDownTimerTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `start ticks down and invokes onFinish`() = runTest {
        val ticks = mutableListOf<Long>()
        var started = false
        var finished = false

        val timer = CancellableCountDownTimer(
            totalSeconds = 3,
            onTick = { ticks.add(it) },
            onFinish = { finished = true },
            onStart = { started = true },
        )

        timer.start()
        advanceTimeBy(3_500)

        assertTrue(started)
        assertEquals(listOf(3L, 2L, 1L, 0L), ticks)
        assertTrue(finished)
    }

    @Test
    fun `stop cancels timer before finish`() = runTest {
        val ticks = mutableListOf<Long>()
        var finished = false

        val timer = CancellableCountDownTimer(
            totalSeconds = 5,
            onTick = { ticks.add(it) },
            onFinish = { finished = true },
        )

        timer.start()
        advanceTimeBy(1_500)
        timer.stop()
        advanceTimeBy(5_000)

        assertFalse(finished)
        assertTrue(ticks.isNotEmpty())
        assertTrue(ticks.size < 6)
    }

    @Test
    fun `start is ignored when already running`() = runTest {
        var startCount = 0
        val timer = CancellableCountDownTimer(
            totalSeconds = 2,
            onStart = { startCount++ },
        )

        timer.start()
        timer.start()
        advanceTimeBy(3_000)

        assertEquals(1, startCount)
    }
}
