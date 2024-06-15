package com.dojah_inc.dojah_android_sdk.domain.responses

import com.google.gson.annotations.SerializedName

data class DataSampleResponse(

    @SerializedName("status") var status: String? = null,
    @SerializedName("data") var data: DataSampleData? = DataSampleData(),
    @SerializedName("message") var message: String? = null

)

data class DataSampleUser(

    @SerializedName("fname") var fname: String? = null,
    @SerializedName("lname") var lname: String? = null,
    @SerializedName("phone") var phone: String? = null

)

data class DataSampleData(

    @SerializedName("transaction_volume") var transactionVolume: Int? = null,
    @SerializedName("weekly_target_count") var weeklyTargetCount: String? = null,
    @SerializedName("transaction_value") var transactionValue: Double? = null,
    @SerializedName("weekly_target_value") var weeklyTargetValue: String? = null,
    @SerializedName("status_count") var statusCount: Int = 0,
    @SerializedName("status_value") var statusValue: Int = 0,
    @SerializedName("user") var user: DataSampleUser? = DataSampleUser(),
    @SerializedName("start_date") var startDate: String? = null,
    @SerializedName("end_date") var endDate: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("last_updated") var lastUpdated: String? = null

)
