package com.dojah.sdk_kyc.domain.responses

data class ValidateOtpResponse(
    val entity: ValidateOtpEntity
)

data class ValidateOtpEntity(
    val valid: Boolean
)