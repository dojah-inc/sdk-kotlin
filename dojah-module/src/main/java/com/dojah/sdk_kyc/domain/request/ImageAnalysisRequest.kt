package com.dojah.sdk_kyc.domain.request

import com.google.gson.annotations.SerializedName

data class ImageAnalysisRequest(
    val image: String,
    @SerializedName("image_type")val imageType: String
)