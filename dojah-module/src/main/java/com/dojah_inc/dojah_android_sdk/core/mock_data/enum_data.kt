package com.dojah_inc.dojah_android_sdk.core.mock_data


val enumData: String = """ 
    {
        "bvn": {
          "name": "Bank Verification Number",
          "abbr": "BVN",
          "subtext": "",
          "subtext2": "",
          "placeholder": "22398337867",
          "enum": "BVN",
          "spanid": "d-none",
          "inputType": "number",
          "inputMode": "numeric",
          "minLength": "11",
          "maxLength": "11"
        },
        "nin": {
          "name": "National Identification Number",
          "idName": "NIN Slip",
          "abbr": "NIN",
          "subtext": "",
          "subtext2": "",
          "placeholder": "56743378909",
          "enum": "NIN",
          "spanid": "d-none",
          "id": "nin-slip",
          "value": "NG-NIN-SLIP",
          "inputType": "number",
          "inputMode": "numeric",
          "minLength": "11",
          "maxLength": "11"
        },
        "vnin": {
          "name": "Virtual National Identification Number",
          "idName": "VNIN",
          "abbr": "VNIN",
          "subtext": "Dial *346*3*NIN*1138183# to generate your VNIN",
          "subtext2": "Note, you can only use the VNIN generated once",
          "placeholder": "SE426838975455SC",
          "enum": "VNIN",
          "spanid": "d-none",
          "id": "vnin",
          "value": "NG-VNIN",
          "inputType": "text",
          "inputMode": "text"
        },
        "dl": {
          "name": "Driver\'s License",
          "abbr": "Driver\'s License",
          "subtext": "",
          "subtext2": "",
          "placeholder": "ABC98765AA77",
          "enum": "DL",
          "id": "drivers-license",
          "value": "NG-DLI",
          "spanid": "d-none",
          "inputType": "text",
          "inputMode": "text"
        },
        "passport": {
          "id": "international-passport",
          "name": "International Passport",
          "value": "NG-PASS"
        },
        "national": {
          "id": "national-id",
          "name": "National ID",
          "value": "NG-NAT"
        },
        "permit": {
          "id": "residence-permit",
          "name": "Residence Permit",
          "value": "UK-RP"
        },
        "custom": {
          "id": "custom-id",
          "name": "OTHERS",
          "value": "NG-CUSTOM"
        },
        "voter": {
          "id": "voter-id",
          "name": "Voter\'s Card",
          "value": "NG-VCARD"
        },
        "mobile": {
          "name": "Phone Number",
          "abbr": "Phone Number",
          "subtext": "The phone number linked to your NIN",
          "subtext2": "",
          "placeholder": "08142697758",
          "enum": "MOBILE",
          "spanid": "",
          "inputType": "number",
          "inputMode": "numeric"
        },
        "NG-DLI": {
          "enum": "DL_ID",
          "idName": "Driver\'s License",
          "id": "dl"
        },
        "NG-PASS": {
          "enum": "PASSPORT_ID",
          "idName": "International Passport",
          "id": "passport"
        },
        "NG-NAT": {
          "enum": "NATIONAL_ID",
          "idName": "National ID",
          "id": "national"
        },
        "UK-RP": {
          "enum": "RESIDENCE_PERMIT",
          "idName": "Residence Permit"
        },
        "NG-CUSTOM": {
          "enum": "CUSTOM_ID",
          "idName": "Custom ID card"
        },
        "NG-VCARD": {
          "enum": "NG-VCARD",
          "idName": "Voter\'s Card",
          "id": "voter"
        },
        "NG-NIN-SLIP": {
          "enum": "NG-NIN-SLIP",
          "idName": "NIN Slip",
          "id": "nin"
        },
        "selfie": {
          "name": "Take a selfie"
        },
        "otp": {
          "name": "OTP (One-time Password)"
        },
        "gh-dl": {
          "name": "Driver\'s License",
          "abbr": "Driver\'s License",
          "subtext": "",
          "subtext2": "",
          "placeholder": "V0000000",
          "enum": "GH-DL",
          "id": "dl",
          "value": "GH-DLI",
          "spanid": "d-none",
          "inputType": "text",
          "inputMode": "text"
        },
        "gh-voter": {
          "name": "Voter\'s Card",
          "abbr": "Voter\'s Card",
          "subtext": "",
          "subtext2": "",
          "placeholder": "V0000000",
          "enum": "GH-VOTER",
          "value": "GH-VOTER",
          "spanid": "d-none",
          "inputType": "text",
          "inputMode": "text",
          "id": "voter"
        },
        "tz-nin": {
          "name": "National Identification Number",
          "abbr": "NIN",
          "subtext": "",
          "subtext2": "",
          "placeholder": "56743378909",
          "enum": "TZ-NIN",
          "spanid": "d-none",
          "inputType": "text",
          "inputMode": "text",
          "id": "nin"
        },
        "ug-id": {
          "name": "Voter/National ID",
          "abbr": "Voter/National ID",
          "subtext": "",
          "subtext2": "",
          "placeholder": "56743378909",
          "enum": "UG-ID",
          "spanid": "d-none",
          "inputType": "text",
          "inputMode": "text"
        },
        "ug-telco": {
          "name": "Telco Subscriber",
          "abbr": "Telco Subscriber",
          "subtext": "",
          "subtext2": "",
          "placeholder": "06743378909",
          "enum": "UG-TELCO",
          "spanid": "d-none",
          "inputType": "number",
          "inputMode": "numeric"
        },
        "ke-dl": {
          "name": "Driver\'s License",
          "abbr": "Driver\'s License",
          "subtext": "",
          "subtext2": "",
          "placeholder": "V0000000",
          "enum": "KE-DL",
          "id": "dl",
          "value": "KE-DLI",
          "spanid": "d-none",
          "inputType": "text",
          "inputMode": "text"
        },
        "ke-id": {
          "name": "National ID",
          "abbr": "National ID",
          "subtext": "",
          "subtext2": "",
          "placeholder": "V0000000",
          "enum": "KE-ID",
          "value": "KE-ID",
          "spanid": "d-none",
          "inputType": "text",
          "inputMode": "text",
          "id": "national"
        },
        "ke-kra": {
          "name": "KRA Pin",
          "abbr": "KRA",
          "subtext": "",
          "subtext2": "",
          "placeholder": "56743378909",
          "enum": "KE-KRA",
          "spanid": "d-none",
          "inputType": "text",
          "inputMode": "text"
        },
        "sa-dl": {
          "name": "Driver\'s License",
          "abbr": "Driver\'s License",
          "subtext": "",
          "subtext2": "",
          "placeholder": "V0000000",
          "enum": "SA-DL",
          "id": "dl",
          "value": "SA-DLI",
          "spanid": "d-none",
          "inputType": "text",
          "inputMode": "text"
        },
        "sa-id": {
          "name": "National ID",
          "abbr": "National ID",
          "subtext": "",
          "subtext2": "",
          "placeholder": "V0000000",
          "enum": "SA-ID",
          "value": "SA-ID",
          "spanid": "d-none",
          "inputType": "text",
          "inputMode": "text",
          "id": "national"
        },
        "cac": {
          "name": "Registered Company Number",
          "abbr": "RC Number",
          "subtext": "",
          "placeholder": "805396",
          "enum": "RC-NUMBER",
          "spanid": "d-none",
          "idName": "cac",
          "inputType": "text",
          "inputMode": "text"
        },
        "tin": {
          "name": "Tax Identification Number",
          "abbr": "TIN",
          "subtext": "",
          "placeholder": "18609323-0001",
          "enum": "TIN",
          "spanid": "d-none",
          "idName": "tin",
          "inputType": "text",
          "inputMode": "text"
        }
      }
""".trimIndent()
