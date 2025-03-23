package com.dojah.kyc_sdk_kotlin.domain.responses

import com.google.gson.annotations.SerializedName


data class CheckIpResponse (

    @SerializedName("entity" ) var entity : IpEntity? = IpEntity()

)

data class IpEntity (

    @SerializedName("status"     ) var status     : String?  = null,
    @SerializedName("country"    ) var country    : String?  = null,
    @SerializedName("regionName" ) var regionName : String?  = null,
    @SerializedName("city"       ) var city       : String?  = null,
    @SerializedName("district"   ) var district   : String?  = null,
    @SerializedName("zip"        ) var zip        : String?  = null,
    @SerializedName("lat"        ) var lat        : Double?  = null,
    @SerializedName("lon"        ) var lon        : Double?  = null,
    @SerializedName("timezone"   ) var timezone   : String?  = null,
    @SerializedName("isp"        ) var isp        : String?  = null,
    @SerializedName("org"        ) var org        : String?  = null,
    @SerializedName("as"         ) var mas         : String?  = null,
    @SerializedName("mobile"     ) var mobile     : Boolean? = null,
    @SerializedName("proxy"      ) var proxy      : Boolean? = null,
    @SerializedName("hosting"    ) var hosting    : Boolean? = null,
    @SerializedName("query"      ) var query      : String?  = null

)