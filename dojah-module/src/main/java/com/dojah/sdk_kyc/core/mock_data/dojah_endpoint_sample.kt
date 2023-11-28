package com.dojah.sdk_kyc.core.mock_data

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
