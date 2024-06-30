package com.dojah_inc.dojah_android_sdk.domain.responses


import com.google.gson.annotations.SerializedName

 data class LivenessCheckResponse(
    val entity: Entity?
) {
     data class Entity(
         @SerializedName("continue_verification") val continueVerification: Boolean?, // true
         val match: Boolean?, // false
         val reason: String? // Failed Liveness Check
    )
}