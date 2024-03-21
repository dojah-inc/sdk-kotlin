package com.dojah.sdk_kyc.domain.responses

import com.google.gson.annotations.SerializedName


data class DecisionResponse(
    @SerializedName("entity") var entity: DecisionResponseEntity? = DecisionResponseEntity()
)

data class DecisionResponseEntity(
    var overallCheck: String? = null,
    var reason: String? = null
)