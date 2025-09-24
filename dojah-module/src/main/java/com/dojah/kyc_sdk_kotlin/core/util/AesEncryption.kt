package com.dojah.kyc_sdk_kotlin.core.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.dojah.kyc_sdk_kotlin.BuildConfig
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AesEncryption {
    private const val ALGORITHM = "AES/CBC/PKCS7Padding"
    private const val KEY_ALGORITHM = "AES"

    @RequiresApi(Build.VERSION_CODES.O)
    fun encrypt(data: String, secret: String): String {
        try {
            val cleanedData =
                data.replace(Regex("^data:(image|application)/(jpeg|png|pdf);base64,"), "")
            val key = secret.toByteArray(Charsets.UTF_8)
            val iv = IvParameterSpec(key)
            val secretKeySpec = SecretKeySpec(key, KEY_ALGORITHM)

            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv)

            val encrypted = cipher.doFinal(cleanedData.toByteArray(Charsets.UTF_8))
            return Base64.getEncoder().encodeToString(encrypted)
        } catch (error: Exception) {
            print(error)
            return data
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.encrypted(): String {
    return AesEncryption.encrypt(this, BuildConfig.AES_ENC_SECRET)
}
