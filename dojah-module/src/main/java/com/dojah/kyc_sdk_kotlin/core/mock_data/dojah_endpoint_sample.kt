package com.dojah.kyc_sdk_kotlin.core.mock_data

fun preAuthResponseSample(
): String = """{
    "widget": {
        "published": true,
        "pages": [
            {
                "page": "user-data"
            },
            {
                "page": "government-data",
                "config": {
                    "bvn": true,
                    "dl": true,
                    "vnin": true,
                    "nin": true,
                    "otp": true,
                    "selfie": false
                }
            },
            {
                "page": "business-data",
                "config": {
                    "cac": true
                }
            },
            {
                "page": "phone-number",
                "config": {
                    "verification": true
                }
            },
            {
                "page": "email",
                "config": {
                    "verification": true
                }
            },
            {
                "page": "business-id"
            },
            {
                "page": "address",
                "config": {
                    "verification": true
                }
            },
            {
                "page": "id",
                "config": {
                    "passport": true,
                    "dl": true,
                    "voter": true,
                    "selfie": true,
                    "otp": true,
                    "national": true
                }
            },
            {
                "page": "selfie",
                "config": {
                    "type": "basic",
                    "version": 3
                }
            },
            {
                "page": "additional-document",
                "config": {
                    "instruction": "Make sure your document is properly placed, and hold it still for a few seconds"
                }
            }
        ],
        "country": [
            "US",
            "UY",
            "UZ",
            "VU",
            "VE",
            "VN",
            "VG",
            "VI",
            "WF",
            "EH",
            "YE",
            "ZM",
            "ZW"
        ],
        "env": "Sandbox",
        "company": {
            "prod_public_key": "prod_pk_Xcc45wv4gn1jh0aBZdMZsTL4x"
        }
    },
    "public_key": "test_pk_PxxjNNcn6fAy1DfOazC2lKKdH",
    "app": {
        "name": "Test App",
        "logo": "https://church-management-app.s3.us-east-2.amazonaws.com/1660286103950.jpeg",
        "color_code": "#36635c",
        "id": "62f5f4a92f94ab003408ba4c"
    }
}
"""

fun authResponseSample(): String = """{
    "session_id": "656dbe56d2c43c0031e7ed56",
    "company_name": "SDK Company ",
    "white_label": false,
    "ucode": "hivdM6",
    "environment": "test",
    "init_data": {
        "success": true,
        "msg": "Operation completed successfully",
        "data": {
            "step_number": 0,
            "reference_id": "DJ-EDF9CAAC99",
            "verification_id": 17066,
            "steps": [
                {
                    "config": {
                        "default": ""
                    },
                    "id": 0,
                    "name": "index"
                },
                {
                    "config": {
                        "default": ""
                    },
                    "id": 1,
                    "name": "countries"
                },
                {
                    "config": {},
                    "id": 2,
                    "name": "user-data"
                },
                {
                    "config": {
                        "bvn": true,
                        "dl": true,
                        "nin": true,
                        "otp": true,
                        "selfie": false,
                        "vnin": true
                    },
                    "id": 3,
                    "name": "government-data"
                },
                {
                    "config": {
                        "otp": true,
                        "selfie": false
                    },
                    "id": 4,
                    "name": "government-data-verification"
                },
                {
                    "config": {
                        "cac": true
                    },
                    "id": 5,
                    "name": "business-data"
                },
                {
                    "config": {
                        "verification": true
                    },
                    "id": 6,
                    "name": "phone-number"
                },
                {
                    "config": {
                        "verification": true
                    },
                    "id": 7,
                    "name": "email"
                },
                {
                    "config": {},
                    "id": 8,
                    "name": "business-id"
                },
                {
                    "config": {
                        "verification": true
                    },
                    "id": 9,
                    "name": "address"
                },
                {
                    "config": {
                        "dl": true,
                        "national": true,
                        "passport": true,
                        "voter": true
                    },
                    "id": 10,
                    "name": "id-options"
                },
                {
                    "config": {
                        "dl": true,
                        "national": true,
                        "otp": true,
                        "passport": true,
                        "selfie": true,
                        "voter": true
                    },
                    "id": 11,
                    "name": "id"
                },
                {
                    "config": {
                        "type": "basic",
                        "version": 3
                    },
                    "id": 12,
                    "name": "selfie"
                },
                {
                    "config": {
                        "instruction": "Make sure your document is properly placed, and hold it still for a few seconds"
                    },
                    "id": 13,
                    "name": "additional-document"
                }
            ]
        }
    },
    "app": {
        "name": "Default App"
    }
} 
"""

fun getIpResponse(): String = """{
        "ip": "54.86.50.139"
    }
""".trimIndent()

fun checkIpResponse(): String = """{
    "entity": {
        "status": "success",
        "country": "United States",
        "regionName": "Virginia",
        "city": "Ashburn",
        "district": "",
        "zip": "20149",
        "lat": 39.0438,
        "lon": -77.4874,
        "timezone": "America/New_York",
        "isp": "Amazon.com, Inc.",
        "org": "AWS EC2 (us-east-1)",
        "as": "AS14618 Amazon.com, Inc.",
        "mobile": false,
        "proxy": false,
        "hosting": true,
        "query": "54.86.50.139"
    }
}
""".trimIndent()

fun simpleEventRequest(
    stepNumber: Int,
    eventType: String,
    eventValue: String,
): String = """{
  "verification_id": 172411,
  "step_number": $stepNumber,
  "event_type": "$eventType",
  "event_value": "$eventValue",
  "services": []
}
""".trimIndent()

fun simpleEventResponse(): String = """{
    "entity":{
        "success":true,
        "msg":"Operation completed successfully"
    }
}
""".trimIndent()

fun bvnResponse(): String = """{
  "entity": {
        "bvn": "22171234567",
        "first_name": "JOHN",
        "last_name": "DOE",
        "middle_name": "AHMED",
        "gender": "Male",
        "date_of_birth": "1997-05-16",
        "phone_number1": "08012345678",
        "image": "BASE 64 IMAGE",
        "phone_number2": "08012345678"
    }
}
""".trimIndent()

fun ninResponse(): String = """{
    "entity": {
        "nin": "70123456789",
        "firstname": "John",
        "middlename": "Doe",
        "surname": "Alamutu",
        "telephoneno": "08012345678",
        "birthdate": "01-01-1982",
        "photo": "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgc...",
        "gender": "m",
    }
}
""".trimIndent()

fun vNinResponse(): String = """{
    "entity": {
        "vnin": "AB012345678910YZ",
        "firstname": "John",
        "middlename": "Doe",
        "surname": "Alamutu",
        "user_id": "WXABCD-1234",
        "gender": "M",
        "mobile": "08012345678",
        "dateOfBirth": "YYYY-MM-DD",
        "photo": "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgc..."
    }
}
""".trimIndent()

fun driverLicenceResponse(): String = """{
    "entity": {
        "uuid": "1625583696",
        "licenseNo": "FKJ494A2133",
        "firstName": "JOHN",
        "lastName": "DOE",
        "middleName": "",
        "gender": "Male",
        "issuedDate": "2019-01-25",
        "expiryDate": "2024-08-17",
        "stateOfIssue": "LAGOS",
        "birthDate": "28-09-1998",
        "photo": "BASE 64 IMAGE"
    }
}
""".trimIndent()


fun sendOtpResponse(): String = """{
    "entity": [
        {
            "reference_id": "6f0d7137-358a-4686-a6db-4720732eac86",
            "destination": "+2347068038410",
            "status_id": "d21daf53-7032-43e1-8fda-c7ddb54a3ada",
            "status": "sms OTP sent successfully"
        }
    ]
}
""".trimIndent()


fun validateOtpResponse(): String = """{
    "entity": {
        "valid": true
    }
}
""".trimIndent()

fun imageAnalysisResponse(): String = """{
      "entity": {
        "face": {
          "face_detected": true,
          "message": "face detected",
          "multiface_detected": false,
          "details": {
            "age_range": {
              "low": 25,
              "high": 33
            },
            "smile": {
              "value": false,
              "confidence": 99.9901123046875
            },
            "gender": {
              "value": "Male",
              "confidence": 99.9473876953125
            },
            "eyeglasses": {
              "value": true,
              "confidence": 100
            },
            "sunglasses": {
              "value": false,
              "confidence": 99.99800109863281
            },
            "beard": {
              "value": true,
              "confidence": 77.6823959350586
            },
            "mustache": {
              "value": false,
              "confidence": 94.59651947021484
            },
            "eyes_open": {
              "value": true,
              "confidence": 98.76229858398438
            },
            "mouth_open": {
              "value": false,
              "confidence": 95.53598022460938
            },
            "emotions": [
              {
                "type": "CALM",
                "confidence": 92.67578125
              },
              {
                "type": "FEAR",
                "confidence": 3.662109375
              },
              {
                "type": "CONFUSED",
                "confidence": 0.01208186149597168
              },
              {
                "type": "DISGUSTED",
                "confidence": 0.0005543231964111328
              },
              {
                "type": "SAD",
                "confidence": 0.00006556510925292969
              },
              {
                "type": "ANGRY",
                "confidence": 0.0000059604644775390625
              },
              {
                "type": "HAPPY",
                "confidence": 0
              },
              {
                "type": "SURPRISED",
                "confidence": 0
              }
            ]
          },
          "quality": {
            "brightness": 65.3563461303711,
            "sharpness": 98.08562469482422
          },
          "confidence": 99.99983215332031,
          "bounding_box": {
            "width": 0.17492765188217163,
            "height": 0.41968902945518494,
            "left": 0.40705186128616333,
            "top": 0.3246147930622101
          }
        }
      }
    }
""".trimIndent()

fun livenessCheckResponse(): String = """{
  "entity": {
    "match": false,
    "reason": "Failed Liveness Check",
    "continue_verification": true
  }
}
""".trimIndent()

fun livenessVerifyResponse(): String = """{
  "entity": {
    "person": {
      "url": "https://dojah-images.s3.amazonaws.com/65b0f14c909cc10031733141face.jpeg",
      "confidence_value": 99.99991607666016
    },
    "id": {},
    "overall": {
      "confidence_value": 100
    },
    "business": {},
    "device": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36",
    "ip": "{'status': 'success', 'country': 'Nigeria', 'regionName': 'Osun State', 'city': 'Osogbo', 'district': '', 'zip': '', 'lat': 7.7614, 'lon': 4.5424, 'timezone': 'Africa/Lagos', 'isp': 'MTN NIGERIA Communication limited', 'org': 'MTN Nigeria', 'as': 'AS29465 MTN NIGERIA Communication limited', 'mobile': True, 'proxy': False, 'hosting': True, 'query': '2c0f:f5c0:732:4377:d959:f963:a836:bdbe'}",
    "reference_id": "DJ-91C56E4EEC"
  }
}
""".trimIndent()
fun additionalDocResponse(): String = """{
    "file_base64": "string",
    "file_type": "string",
    "file_name": "string",
    "verification_id": 0
}
""".trimIndent()

fun decisionResponse():String = """{
  "entity": {
    "overallCheck": "approved",
    "reason": "Verification successful"
  }
}
""".trimIndent()

fun cacLookUpResponse():String = """{
      "entity": {
        "company_name": "EFO GLOBAL SYSTEMS LIMITED",
        "rc_number": "805396",
        "date_of_registration": "2009-03-04T23:00:00+00:00",
        "address": "7,PIPELINELAYOU,BESIDEREFINARY,EKPAN,DELTA",
        "type_of_company": "COMPANY",
        "business": "263c2027-793f-4c10-9d08-04abced2f1c7",
        "status": "ACTIVE"
      }
    }
""".trimIndent()
