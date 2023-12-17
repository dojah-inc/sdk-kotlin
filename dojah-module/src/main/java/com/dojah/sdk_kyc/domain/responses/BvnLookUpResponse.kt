package com.dojah.sdk_kyc.domain.responses

import com.google.gson.annotations.SerializedName


data class BvnLookUpResponse(
    @SerializedName("entity") override val entity: BvnLookUpEntity? = BvnLookUpEntity()
) : GovIdInterface<BvnLookUpEntity>

data class BvnLookUpEntity(
    @SerializedName("date_of_birth") override var dob: String? = null,
    @SerializedName("first_name") override var fName: String? = null,
    @SerializedName("middle_name") override var mName: String? = null,
    override var licenseNum: String? = null,
    @SerializedName("last_name") override var lName: String? = null,
    @SerializedName("image") override var image: String? = null,
    @SerializedName("bvn") var bvn: String? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("phone_number1") var phoneNumber1: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("enrollment_bank") var enrollmentBank: String? = null,
    @SerializedName("enrollment_branch") var enrollmentBranch: String? = null,
    @SerializedName("level_of_account") var levelOfAccount: String? = null,
    @SerializedName("lga_of_origin") var lgaOfOrigin: String? = null,
    @SerializedName("lga_of_residence") var lgaOfResidence: String? = null,
    @SerializedName("marital_status") var maritalStatus: String? = null,
    @SerializedName("name_on_card") var nameOnCard: String? = null,
    @SerializedName("nationality") var nationality: String? = null,
    @SerializedName("nin") var nin: String? = null,
    @SerializedName("phone_number2") var phoneNumber2: String? = null,
    @SerializedName("registration_date") var registrationDate: String? = null,
    @SerializedName("residential_address") var residentialAddress: String? = null,
    @SerializedName("state_of_origin") var stateOfOrigin: String? = null,
    @SerializedName("state_of_residence") var stateOfResidence: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("watch_listed") var watchListed: String? = null,
) : GovIdEntityInterface{
    override val phoneNumber: String?
        get() = phoneNumber1?:phoneNumber2
}

