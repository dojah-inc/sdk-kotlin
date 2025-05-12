package com.dojah.kyc_sdk_kotlin.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ExtraUserData(
    val userData: UserData? = null,
    val govData: GovData? = null,
    val govId: GovId? = null,
    val location: Location? = null,
    val businessData: BusinessData? = null,
    val address: String? = null,
    val metadata: Map<String, Any>? = null
) : Serializable {
}

data class UserData(
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    // format: dd-mm-yyy
    @SerializedName("dob") var dob: String? = null,
    @SerializedName("email") var email: String? = null,
) : Serializable {
    fun allFieldsFilled(): Boolean {
        return firstName?.isNotEmpty() == true &&
                lastName?.isNotEmpty() == true &&
                dob?.isNotEmpty() == true
    }

    fun atLeastOneFieldFilled(): Boolean {
        return firstName?.isNotEmpty() == true ||
                lastName?.isNotEmpty() == true ||
                dob?.isNotEmpty() == true
    }
}

data class GovData(
    //bvn
    @SerializedName("bvn") var bvn: String? = null,
    //dl
    @SerializedName("dl") var dl: String? = null,
    //nin
    @SerializedName("nin") var nin: String? = null,
    //vnin
    @SerializedName("vnin") var vnin: String? = null,
) : Serializable

//govid: object - national, permit, passport, dl, voter, nin, others
data class GovId(
    //national:Image Url
    @SerializedName("national") var national: String? = null,
    //passport:Image Url
    @SerializedName("passport") var passport: String? = null,
    //dl:Image Url
    @SerializedName("dl") var dl: String? = null,
    //voter:Image Url
    @SerializedName("voter") var voter: String? = null,
    //nin:Image Url
    @SerializedName("nin") var nin: String? = null,
    //others:Image Url
    @SerializedName("others") var others: String? = null,
) : Serializable

//location: object - latitude longitude
data class Location(
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null,
) : Serializable

//businessData: object - cac
data class BusinessData(
    @SerializedName("cac") var cac: String? = null,
) : Serializable