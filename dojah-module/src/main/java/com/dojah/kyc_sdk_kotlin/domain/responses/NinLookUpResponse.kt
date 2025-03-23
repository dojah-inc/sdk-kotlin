package com.dojah.kyc_sdk_kotlin.domain.responses

import com.google.gson.annotations.SerializedName


data class NinLookUpResponse(
    @SerializedName("entity") override var entity: NinLookUpEntity? = NinLookUpEntity()
) : GovIdInterface<NinLookUpEntity>

data class NinLookUpEntity(
    @SerializedName("nin") var nin: String? = null,
    @SerializedName("firstname") var firstname: String? = null,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("middlename") var middlename: String? = null,
    @SerializedName("middle_name") var middleName: String? = null,
    @SerializedName("surname") var surname: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("maidenname") var maidenname: String? = null,
    @SerializedName("telephoneno") var telephoneno: String? = null,
    @SerializedName("phone_number") var phoneNum: String? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("place") var place: String? = null,
    @SerializedName("profession") var profession: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("height") var height: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("birthdate") var birthdate: String? = null,
    @SerializedName("date_of_birth") var dateOfBirth: String? = null,
    @SerializedName("birthstate") var birthstate: String? = null,
    @SerializedName("birthcountry") var birthcountry: String? = null,
    @SerializedName("centralID") var centralID: String? = null,
    @SerializedName("documentno") var documentno: String? = null,
    @SerializedName("educationallevel") var educationallevel: String? = null,
    @SerializedName("employmentstatus") var employmentstatus: String? = null,
    @SerializedName("nok_firstname") var nokFirstname: String? = null,
    @SerializedName("nok_lastname") var nokLastname: String? = null,
    @SerializedName("nok_middlename") var nokMiddlename: String? = null,
    @SerializedName("nok_address1") var nokAddress1: String? = null,
    @SerializedName("nok_address2") var nokAddress2: String? = null,
    @SerializedName("nok_lga") var nokLga: String? = null,
    @SerializedName("nok_state") var nokState: String? = null,
    @SerializedName("nok_town") var nokTown: String? = null,
    @SerializedName("nok_postalcode") var nokPostalcode: String? = null,
    @SerializedName("othername") var othername: String? = null,
    @SerializedName("pfirstname") var pfirstname: String? = null,
    @SerializedName("photo") var photo: String? = null,
    @SerializedName("pmiddlename") var pmiddlename: String? = null,
    @SerializedName("psurname") var psurname: String? = null,
    @SerializedName("nspokenlang") var nspokenlang: String? = null,
    @SerializedName("ospokenlang") var ospokenlang: String? = null,
    @SerializedName("religion") var religion: String? = null,
    @SerializedName("residence_Town") var residenceTown: String? = null,
    @SerializedName("residence_lga") var residenceLga: String? = null,
    @SerializedName("residence_state") var residenceState: String? = null,
    @SerializedName("residencestatus") var residencestatus: String? = null,
    @SerializedName("residence_AddressLine1") var residenceAddressLine1: String? = null,
    @SerializedName("residence_AddressLine2") var residenceAddressLine2: String? = null,
    @SerializedName("self_origin_lga") var selfOriginLga: String? = null,
    @SerializedName("self_origin_place") var selfOriginPlace: String? = null,
    @SerializedName("self_origin_state") var selfOriginState: String? = null,
    @SerializedName("signature") var signature: String? = null,
    @SerializedName("nationality") var nationality: String? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("trackingId") var trackingId: String? = null,
    val customer: String? = null,
    val uuid: String? = null
) : GovIdEntityInterface {
    override val dob: String?
        get() = dateOfBirth?:birthdate
    override val fName: String?
        get() = firstname?:firstName
    override val mName: String?
        get() = middlename?:middleName
    override val licenseNum: String?
        get() = null
    override val lName: String?
        get() = surname?:lastName
    override val image: String?
        get() = photo
    override val phoneNumber: String?
        get() = telephoneno?:phoneNum
    override val customerID: String?
        get() = customer?:uuid

}

