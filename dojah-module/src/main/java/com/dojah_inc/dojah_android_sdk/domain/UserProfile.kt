package com.dojah_inc.dojah_android_sdk.domain

import com.google.gson.annotations.SerializedName

data class UserProfile(
        @SerializedName("id") val userId: String? = null,

        @SerializedName("uuid") val uuid: String? = null,

        @SerializedName("device_mac") val deviceMac: String,

        @SerializedName("device_manufacturer") val deviceManufacturer: String,

        @SerializedName("emailverified") val emailVerified: Int,

        @SerializedName("hastransactionpin") val hasTransactionPIN: Int,

        val phone: String,

        val email: String,

        @SerializedName("printer_mac") val printerMac: String,

        @SerializedName("terminal_id") val terminalID: String?,

        @SerializedName("terminal_serial") val serial: String? = null,

        @SerializedName("terminal_name") val terminalName: String,

        @SerializedName("terminal_address") val terminalAddress: String? = null,

        @SerializedName("notification_groups") val notificationGroups: List<String>? = null,

        @SerializedName("passport") val profilePic: String? = null,

        @SerializedName("kippa") val receipt: ReceiptInfo? = null,

        @SerializedName("account") val account: AccountInfo? = null,

        @SerializedName("support") val support: SupportInfo? = null,


        @SerializedName("gtb_wallet_id") val walletID: String? = null,

        @SerializedName("login_time") val firstLoginTime: String? = null,

        @SerializedName("role") val roleID: Int? = null,

        @SerializedName("intercom_hash") val intercomHash: String? = null,


        @SerializedName("probation") val probation: Boolean = false
)

data class AccountInfo(
        @SerializedName("account_number") val accountNumber: String? = null,

        @SerializedName("account_name") val accountName: String? = null,

        val bank: String? = null
)

data class ReceiptInfo(
        val phone: String? = null,

        val email: String? = null,

        val info: String? = null
)

data class SupportInfo(
        @SerializedName("call_number") val callNumber: String? = null,

        @SerializedName("whatsapp_link") val whatsappLink: String? = null,

        @SerializedName("email") val email: String? = null
)