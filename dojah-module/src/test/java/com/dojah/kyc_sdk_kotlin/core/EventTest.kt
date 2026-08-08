package com.dojah.kyc_sdk_kotlin.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

class EventTest {

    @Test
    fun `getContentIfNotHandled returns content on first access`() {
        val event = Event("payload")

        assertEquals("payload", event.getContentIfNotHandled())
    }

    @Test
    fun `getContentIfNotHandled returns null on second access`() {
        val event = Event("payload")

        event.getContentIfNotHandled()

        assertNull(event.getContentIfNotHandled())
    }

    @Test
    fun `peekContent always returns content after consumption`() {
        val event = Event("payload")

        event.getContentIfNotHandled()

        assertEquals("payload", event.peekContent())
    }

    @Test
    fun `hasBeenHandled is false before consumption`() {
        val event = Event("payload")

        assertFalse(event.hasBeenHandled)
    }
}
