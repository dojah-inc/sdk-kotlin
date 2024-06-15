package com.dojah_inc.dojah_android_sdk.domain.responses

import com.google.gson.annotations.SerializedName


data class DecisionResponse(
    @SerializedName("entity") var entity: DecisionResponseEntity? = DecisionResponseEntity()
)

data class DecisionResponseEntity(
    var overallCheck: String? = null,
    var reason: String? = null
)