package com.dojah.sdk_kyc.domain.responses

data class DriverLicenceResponse(
    override val entity: DriverEntity?
) : GovIdInterface<DriverEntity>

data class DriverEntity(
    val birthDate: String?,
    val expiryDate: String?,
    val firstName: String?,
    val gender: String?,
    val issuedDate: String?,
    val lastName: String?,
    val licenseNo: String?,
    val middleName: String?,
    val photo: String?,
    val stateOfIssue: String?,
    val uuid: String?
) : GovIdEntityInterface {
    override val dob: String?
        get() = birthDate
    override val fName: String?
        get() = firstName
    override val mName: String?
        get() = middleName
    override val licenseNum: String?
        get() = licenseNo
    override val lName: String?
        get() = lastName
    override val image: String?
        get() = photo

    override val phoneNumber: String?
        get() = null

}