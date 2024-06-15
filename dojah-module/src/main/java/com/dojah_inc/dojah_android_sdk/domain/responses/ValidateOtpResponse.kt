package com.dojah_inc.dojah_android_sdk.domain.responses

data class ValidateOtpResponse(
    val entity: ValidateOtpEntity
)

data class ValidateOtpEntity(
    val valid: Boolean
)