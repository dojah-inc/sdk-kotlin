package com.dojah.kyc_sdk_kotlin.data.security

import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
class SecurityManagerTest {

    private lateinit var securityManager: SecurityManager

    @Before
    fun setUp() {
        val prefManager = mockk<SharedPreferenceManager>(relaxed = true)
        securityManager = SecurityManager(prefManager)
    }

    @Test
    fun `encrypt and decrypt round trip preserves plaintext`() {
        val plaintext = "+2348012345678"

        val encrypted = securityManager.encrypt(plaintext)
        val decrypted = securityManager.decrypt(encrypted)

        assertNotEquals(plaintext, encrypted.trim())
        assertEquals(plaintext, decrypted)
    }

    @Test
    fun `encrypt and decrypt round trip handles unicode`() {
        val plaintext = "pässwörd-用户"

        val encrypted = securityManager.encrypt(plaintext)
        val decrypted = securityManager.decrypt(encrypted)

        assertEquals(plaintext, decrypted)
    }

    @Test
    fun `encrypt and decrypt round trip handles empty string`() {
        val plaintext = ""

        val encrypted = securityManager.encrypt(plaintext)
        val decrypted = securityManager.decrypt(encrypted)

        assertEquals(plaintext, decrypted)
    }

    @Test
    fun `encrypt produces stable output for same input`() {
        val plaintext = "stable-secret"

        val first = securityManager.encrypt(plaintext)
        val second = securityManager.encrypt(plaintext)

        assertEquals(first.trim(), second.trim())
    }
}
