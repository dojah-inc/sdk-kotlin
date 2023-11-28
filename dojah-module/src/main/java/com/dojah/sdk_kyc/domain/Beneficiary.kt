package com.dojah.sdk_kyc.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Beneficiary(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name = "user_number") val userNumber: String, //The current user of the app
        @ColumnInfo(name = "account_name") val accountName: String, //The account name of the beneficiary
        @ColumnInfo(name = "account_number") val accountNumber: String, /*The account number can also be the phone number in case of airtime*/
        @ColumnInfo(name = "service_id") val serviceId: Int, //The id of the company eg Service Providers id or the Bank code
        @ColumnInfo(name = "category_id") val categoryId: Int, /* The id of the service eg Airtime, Fund, Transfer*/
        @ColumnInfo(name = "last_transaction_time") val lastTransactionTime: Long) : Parcelable

//Since Fund, Transfer don't have serviceId of their own, let 100 and 200 represent it