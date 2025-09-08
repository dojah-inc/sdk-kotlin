package com.dojah.kyc_sdk_kotlin.core.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.dojah.kyc_sdk_kotlin.BuildConfig
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

object AesEncryption {
    private const val ALGORITHM = "AES/CBC/PKCS5Padding"
    private const val KEY_ALGORITHM = "PBKDF2WithHmacSHA256"
    private const val SALT = "fixed_salt_12345" // In production, use a random salt and store it
    private const val ITERATIONS = 100000
    private const val KEY_LENGTH = 256

    fun generateKey(secret: String): SecretKeySpec {
        val spec = PBEKeySpec(secret.toCharArray(), SALT.toByteArray(), ITERATIONS, KEY_LENGTH)
        val factory = SecretKeyFactory.getInstance(KEY_ALGORITHM)
        val keyBytes = factory.generateSecret(spec).encoded
        return SecretKeySpec(keyBytes, "AES")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encrypt(data: String, secret: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        val iv = ByteArray(16)
        SecureRandom().nextBytes(iv)
        val ivSpec = IvParameterSpec(iv)
        val keySpec = generateKey(secret)

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        val combined = iv + encrypted
        return Base64.getEncoder().encodeToString(combined)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decrypt(encryptedData: String, secret: String): String {
        val decoded = Base64.getDecoder().decode(encryptedData)
        val iv = decoded.copyOfRange(0, 16)
        val ciphertext = decoded.copyOfRange(16, decoded.size)

        val cipher = Cipher.getInstance(ALGORITHM)
        val ivSpec = IvParameterSpec(iv)
        val keySpec = generateKey(secret)

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val decrypted = cipher.doFinal(ciphertext)
        return String(decrypted, Charsets.UTF_8)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.encrypted(): String {
    return AesEncryption.encrypt(this, BuildConfig.AES_ENC_SECRET)
}
