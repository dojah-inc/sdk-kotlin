package com.dojah_inc.dojah_android_sdk.ui.utils

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.containsADigitChar(): Boolean {
    val p: Pattern = Pattern.compile("^(?=.*?[0-9]).+$")
    // Find match between given string
    // & regular expression
    val m: Matcher = p.matcher(this)
    return m.matches()
}

fun String.addChars(): Int {
    return toCharArray().sumOf {
        it.code
    }
}

fun String.convertBalanceToNumber(): Double {
    if (isEmpty()) return -1.0

    val startIndex = if (this[0].isDigit()) 0 else 1

    val balance = substring(startIndex, length)

    return balance.replace(",".toRegex(), "").toDouble()
}

fun String.getInitials(): String {
    return if (isNullOrEmpty()) ""
    else {
        val splitName = split("\\s+".toRegex())

        return if (splitName.size > 1) {
            splitName[0][0].toString() + splitName[1][0].toString()

        } else {
            (this[0] + this[length - 1].toString())
        }
    }
}

fun String.normalize(): String {
    return lowercase().run {
        split("\\s+".toRegex()).joinToString(separator = " ") {
            if (it.isNotEmpty()) it.replaceRange(0, 1, it[0].uppercase())
            else it
        }
    }
}

fun String?.formatAnalysisDate(showMonth: Boolean=false, pattern: String = "yyyy-MM-dd"): String {
    val serverDate: String?= this
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    val date: Date? = try {
        format.parse(serverDate ?: "")
    } catch (e: Exception) {
        Timber.e(e)
        null
    }

    fun getDayNumberSuffix(day: Int): String {
        return when {
            day in 11..13 -> "th"
            day % 10 == 1 -> "st"
            day % 10 == 2 -> "nd"
            day % 10 == 3 -> "rd"
            else -> "th"
        }
    }
    if (date != null) {
        val calendar = Calendar.getInstance().apply {
            time = date
        }

        val dayName = calendar.getDisplayName(
            Calendar.DAY_OF_WEEK,
            Calendar.SHORT,
            Locale.getDefault()
        )
        val dayNumber = calendar[Calendar.DATE]

        val monthName = calendar.getDisplayName(
            Calendar.MONTH,
            Calendar.SHORT,
            Locale.getDefault()
        )
        return "$dayName $dayNumber${
            getDayNumberSuffix(
                dayNumber
            )
        } ${
            if (showMonth) {
                "of $monthName"
            } else ""
        } "
    }

    return ""
}
