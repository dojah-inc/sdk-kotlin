package com.dojah.kyc_sdk_kotlin.ui.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat

fun Number.formatToBalance(currency: String = ""): String {
    return NumberFormat.getInstance().run {

        maximumIntegerDigits = Int.MAX_VALUE
        minimumFractionDigits = 2
        maximumFractionDigits = 2

        format(this@formatToBalance).run {

            val decimalIndex = lastIndexOf('.')
            val decimalDigits = substring(IntRange(decimalIndex + 1, length - 1))

            val formattedBalance = if (decimalDigits.toInt() > 0) this
            else substring(IntRange(0, decimalIndex - 1))

            buildString {
                append(formattedBalance)
                insert(if(formattedBalance[0] == '-') 1 else 0, currency)
            }.trim()
        }
    }
}


// grade number like e.g 1st, 2nd, 3rd...
fun Int?.withGradeSuffix(): String? {
    if (this == null) {
        return null
    }
    val day: Int = this
    return this.toString() + when {
        day in 11..13 -> "th"
        day % 10 == 1 -> "st"
        day % 10 == 2 -> "nd"
        day % 10 == 3 -> "rd"
        else -> "th"
    }
}

fun Double.roundOffDecimal(
    roundMode: RoundingMode = RoundingMode.CEILING,
    pattern: String = "#.##"
): Double {
    val df = DecimalFormat(pattern)
    df.roundingMode = roundMode
    return df.format(this).toDouble()
}