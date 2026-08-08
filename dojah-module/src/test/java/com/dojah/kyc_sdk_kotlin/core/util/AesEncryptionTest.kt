package com.dojah.kyc_sdk_kotlin.core.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
class AesEncryptionTest {

    private val secret = "6543210987654321"

    @Test
    fun `encrypt produces base64 ciphertext different from plaintext`() {
        val plaintext = "sensitive-value"

        val encrypted = AesEncryption.encrypt(plaintext, secret)

        assertNotEquals(plaintext, encrypted)
        assertTrue(encrypted.matches(Regex("^[A-Za-z0-9+/=]+$")))
    }

    @Test
    fun `encrypt strips data URI prefix before encryption`() {
        val withPrefix = "data:image/jpeg;base64,abc123"
        val withoutPrefix = "abc123"

        val encryptedWithPrefix = AesEncryption.encrypt(withPrefix, secret)
        val encryptedWithoutPrefix = AesEncryption.encrypt(withoutPrefix, secret)

        assertEquals(encryptedWithPrefix, encryptedWithoutPrefix)
    }

    @Test
    fun `encrypt handles empty input`() {
        val encrypted = AesEncryption.encrypt("", secret)

        assertTrue(encrypted.isNotEmpty())
    }

    @Test
    fun `encrypt handles unicode input`() {
        val plaintext = "用户-データ-🔒"

        val encrypted = AesEncryption.encrypt(plaintext, secret)

        assertNotEquals(plaintext, encrypted)
        assertTrue(encrypted.isNotEmpty())
    }

    @Test
    fun `encrypt is deterministic for same input and secret`() {
        val plaintext = "repeatable-value"

        val first = AesEncryption.encrypt(plaintext, secret)
        val second = AesEncryption.encrypt(plaintext, secret)

        assertEquals(first, second)
    }

    @Test
    fun `encrypt returns original data when secret is invalid length`() {
        val plaintext = "fallback-value"
        val invalidSecret = "short"

        val result = AesEncryption.encrypt(plaintext, invalidSecret)

        assertEquals(plaintext, result)
    }
}
