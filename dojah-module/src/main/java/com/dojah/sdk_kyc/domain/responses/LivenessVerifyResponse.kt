package com.dojah.sdk_kyc.domain.responses


import com.google.gson.annotations.SerializedName

data class LivenessVerifyResponse(
    val entity: Entity?
) {
    data class Entity(
        val business: Business?,
        val device: String?, // Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36
        val id: Map<*,*>?,
        val ip: String?, // {'status': 'success', 'country': 'Nigeria', 'regionName': 'Osun State', 'city': 'Osogbo', 'district': '', 'zip': '', 'lat': 7.7614, 'lon': 4.5424, 'timezone': 'Africa/Lagos', 'isp': 'MTN NIGERIA Communication limited', 'org': 'MTN Nigeria', 'as': 'AS29465 MTN NIGERIA Communication limited', 'mobile': True, 'proxy': False, 'hosting': True, 'query': '2c0f:f5c0:732:4377:d959:f963:a836:bdbe'}
        val overall: Overall?,
        val person: Person?,
        @SerializedName("reference_id") val referenceId: String? // DJ-91C56E4EEC
    ) {
        class Business

        data class Overall(
            @SerializedName("confidence_value") val confidenceValue: Int? // 100
        )

        data class Person(
            @SerializedName("confidence_value") val confidenceValue: Double?, // 99.99991607666016
            val url: String? // https://dojah-images.s3.amazonaws.com/65b0f14c909cc10031733141face.jpeg
        )
    }
}