package com.dojah_inc.dojah_android_sdk.domain

import com.google.gson.annotations.SerializedName

data class State(@SerializedName("value") val id: Int,
                 @SerializedName("label") val title: String)

data class States(@SerializedName("states") val states: List<State>)