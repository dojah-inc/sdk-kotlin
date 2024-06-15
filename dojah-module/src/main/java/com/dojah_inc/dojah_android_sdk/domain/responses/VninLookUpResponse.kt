package com.dojah_inc.dojah_android_sdk.domain.responses

import com.google.gson.annotations.SerializedName

data class VninLookUpResponse(
    override val entity: VninEntity?
) : GovIdInterface<VninEntity>

data class VninEntity(
    val dateOfBirth: String?,
    @SerializedName("firstname")
    val firstName: String?,
    val gender: String?,
    @SerializedName("middlename")
    val middleName: String?,
    val mobile: String?,
    val photo: String?,
    val surname: String?,
    @SerializedName("user_id")
    val userIdd: String?,
    val vnin: String?,
) : GovIdEntityInterface {
    override val dob: String?
        get() = dateOfBirth
    override val fName: String?
        get() = firstName
    override val mName: String?
        get() = middleName
    override val licenseNum: String?
        get() = null
    override val lName: String?
        get() = surname
    override val image: String?
        get() = photo
    override val phoneNumber: String?
        get() = mobile
    override val customerID: String?
        get() = userIdd
}