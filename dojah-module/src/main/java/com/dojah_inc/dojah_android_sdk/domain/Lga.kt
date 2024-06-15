package com.dojah_inc.dojah_android_sdk.domain

import com.google.gson.annotations.SerializedName

data class Lga(@SerializedName("value") val id: Int,
               @SerializedName("label") val title: String,
               @SerializedName("state_id") var stateId: Int = -1)

data class Lgas(@SerializedName("lga") val lgas: List<Lga>)
