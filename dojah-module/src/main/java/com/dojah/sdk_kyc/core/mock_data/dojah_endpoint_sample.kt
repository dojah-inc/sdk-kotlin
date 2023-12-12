package com.dojah.sdk_kyc.core.mock_data

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

fun weeklyTxnSample(
    daily_txn_count_value: Int = 78,
    daily_txn_amount_value: Double = 425750.0,
    daily_txn_count_goal: Int = 98,
    daily_txn_amount_goal: Double = 750000.0,
): String = """{
    "status": "00",
    "data": {
        "transaction_volume": $daily_txn_count_value,
        "transaction_value": $daily_txn_amount_value,
        "weekly_target_count": "$daily_txn_count_goal",
        "weekly_target_value": "$daily_txn_amount_goal",
        "user": {
            "fname": "Jossy ventures",
            "lname": "Jossy ventures",
            "phone": "08057175725"
        },
        "status_count": 1,
        "status_value": -1,
        "start_date": "2023-08-07",
        "end_date": "2023-08-13",
        "created_at": "2023-08-03",
        "last_updated": "2023-08-03"
    },
    "message": "Agent weekly transactions fetched successfully"
}
"""
