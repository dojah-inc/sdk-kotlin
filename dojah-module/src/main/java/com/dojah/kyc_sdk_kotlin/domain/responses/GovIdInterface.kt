package com.dojah.kyc_sdk_kotlin.domain.responses

interface GovIdInterface <T:GovIdEntityInterface>  {
    val entity: T?
}


interface GovIdEntityInterface {
    val dob: String?
    val fName: String?
    val mName: String?
    val licenseNum: String?
    val lName: String?
    val image: String?
    val phoneNumber: String?
    val customerID: String?
}
