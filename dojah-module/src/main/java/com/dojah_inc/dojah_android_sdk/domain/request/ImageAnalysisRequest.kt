package com.dojah_inc.dojah_android_sdk.domain.request

import com.google.gson.annotations.SerializedName

data class ImageAnalysisRequest(
    val image: String,
    @SerializedName("image_type")val imageType: String
)