package com.dojah.kyc_sdk_kotlin.ui.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StringExtTest {

    @Test
    fun `toTitleCase capitalizes each word`() {
        assertEquals("John Doe", "john doe".toTitleCase())
    }

    @Test
    fun `normalize capitalizes first letter of each word`() {
        assertEquals("Lagos State", "lagos state".normalize())
    }

    @Test
    fun `getInitials returns first letters for two word names`() {
        assertEquals("JD", "John Doe".getInitials())
    }

    @Test
    fun `getInitials returns first and last character for single word`() {
        assertEquals("Ae", "Ade".getInitials())
    }

    @Test
    fun `getInitials returns empty string for empty input`() {
        assertEquals("", "".getInitials())
    }

    @Test
    fun `containsADigitChar returns true when digit present`() {
        assertTrue("abc1".containsADigitChar())
        assertFalse("abc".containsADigitChar())
    }

    @Test
    fun `convertBalanceToNumber strips currency symbol and commas`() {
        assertEquals(1_000_000.00012, "₦1,000,000.00012".convertBalanceToNumber(), 0.00001)
    }
}
