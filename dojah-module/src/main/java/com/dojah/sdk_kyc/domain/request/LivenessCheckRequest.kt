package com.dojah.sdk_kyc.domain.request


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class LivenessCheckRequest(
    val image: String?, // /9j/4AAQSkZJRgABAQEAkACQAAD/2wBDAAIBAQIBAQICAgICAgICAwUDAwMDAwYEBAMFBwYHBwcGBwcICQsJCAgKCAcHCg0KCgsMDAwMBwkODw0MDgsMDAz/+NBg2bTzcy5bgm4TLDwR8NWm9urA70iGzwl9bWuYGyXKskPPPUOH+c8nAJPKwMbkhoXqyIvP0eNEjRHRYpu5xJJ3kru+HDbDYIbBYAWA5giIi+S+iIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIi//9k=
    @SerializedName("verification_id") val verificationId: Int?, // 17567
    @SerializedName("step_number") val stepNumber: Int?, // 3
    val param: String?, // face
    @SerializedName("selfie_type") val selfieType: String?, // single
    @SerializedName("doc_type") val docType: String?, // image
    @SerializedName("continue_verification") val continueVerification: Boolean?, // true

)