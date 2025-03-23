package com.dojah.kyc_sdk_kotlin

import java.util.*

object Base64Test {

    @JvmStatic
    fun encodeToString(input: ByteArray, flags: Int):String{
        return Base64.getEncoder().encodeToString(input)
    }

    @JvmStatic
    fun decode(string: String, flags: Int): ByteArray{
        return Base64.getDecoder().decode(string)
    }
}