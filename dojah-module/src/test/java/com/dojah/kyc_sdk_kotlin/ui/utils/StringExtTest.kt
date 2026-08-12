package com.dojah.kyc_sdk_kotlin.ui.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
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

    @Test
    fun `convertBalanceToNumber returns negative one for empty string`() {
        assertEquals(-1.0, "".convertBalanceToNumber(), 0.0)
    }

    @Test
    fun `normaliseColor pads short hex codes`() {
        assertEquals(0xFF000000.toInt(), "#0".normaliseColor())
    }

    @Test
    fun `normaliseColor parses six digit hex`() {
        assertEquals(0xFF36635C.toInt(), "#36635c".normaliseColor())
    }

    @Test
    fun `addChars sums character codes`() {
        assertEquals('A'.code + 'B'.code, "AB".addChars())
    }

    @Test
    fun `formatAnalysisDate formats known date`() {
        val formatted = "2024-01-15".formatAnalysisDate(showMonth = true)
        assertTrue(formatted.contains("15th"))
        assertTrue(formatted.contains("of"))
    }

    @Test
    fun `formatAnalysisDate returns empty string for invalid date`() {
        assertEquals("", "not-a-date".formatAnalysisDate())
    }
}
