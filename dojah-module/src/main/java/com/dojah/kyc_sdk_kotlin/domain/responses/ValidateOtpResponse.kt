package com.dojah.kyc_sdk_kotlin.domain.responses

data class ValidateOtpResponse(
    val entity: ValidateOtpEntity
)

data class ValidateOtpEntity(
    val valid: Boolean
)