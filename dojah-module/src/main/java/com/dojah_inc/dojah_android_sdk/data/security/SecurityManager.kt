package com.dojah_inc.dojah_android_sdk.data.security

import android.util.Base64
import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecurityManager @Inject constructor(
        private val prefManager: SharedPreferenceManager
) {
    private companion object {
        const val CIPHER = "AES/CBC/PKCS5Padding"
        const val INITIALIZATION_VECTOR = "8119745113154120"
        const val PLAIN_TEXT = "gsgqGmDGsggGWyyItoKDUKDLOpod"
        const val SECRET_KEY_INSTANCE = "PBKDF2WithHmacSHA1"
        const val PASSWORD_ITERATIONS = 30
        const val KEY_SIZE = 128
    }

    fun encrypt(text: String): String {
        val number = "item to encrypt like phone number or password"

        val keySpec = SecretKeySpec(getKey(PLAIN_TEXT, number), "AES")

        return Cipher.getInstance(CIPHER).run {
            init(Cipher.ENCRYPT_MODE, keySpec, IvParameterSpec(INITIALIZATION_VECTOR.toByteArray()))

            val bytes = doFinal(text.toByteArray())

            Base64.encodeToString(bytes, Base64.DEFAULT)
        }
    }

    fun decrypt(text: String): String {
        val number = "item to decrypt like phone number or password"

        val decoded = Base64.decode(text, Base64.DEFAULT)

        val keySpec = SecretKeySpec(getKey(PLAIN_TEXT, number), "AES")

        return Cipher.getInstance(CIPHER).run {
            init(Cipher.DECRYPT_MODE, keySpec, IvParameterSpec(INITIALIZATION_VECTOR.toByteArray()))

            val decrypted = doFinal(decoded)

            String(decrypted, Charsets.UTF_8)
        }
    }

    private fun getKey(text: String, salt: String): ByteArray {
        val keyFactory = SecretKeyFactory.getInstance(SECRET_KEY_INSTANCE)

        val spec = PBEKeySpec(text.toCharArray(), salt.toByteArray(), PASSWORD_ITERATIONS, KEY_SIZE)

        return keyFactory.generateSecret(spec).encoded
    }
}