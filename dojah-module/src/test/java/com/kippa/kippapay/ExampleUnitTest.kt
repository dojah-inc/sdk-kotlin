package com.kippa.kippapay

import com.google.i18n.phonenumbers.PhoneNumberUtil
import org.junit.Test
import java.text.NumberFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        println(String.format("%-48s", "344FFFFFF2G1DS2X3DS1CF3C2G4GTH5H74H3H3G5H6J4IDURHRHFFFFF").replace(' ', 'F'))

        println(parseHexStr2Byte("62994900000000000002").contentToString())
//        val test = "{\"BankTransInfoRequest\":{\"CardExpDate\":\"1209\",\"CardFallback\":false,\"CardNo\":\"5413330089020011\",\"CardSN\":\"\",\"CardTk2\":\"\",\"CardDetectMode\":1,\"IccData\":\"\",\"MsgAmount\":\"000000012000\",\"MsgCurrencyCode\":\"344\",\"StatusOffline\":false},\"MsgUuid\":\"a21a5f171d6a42e0962cbab7a93d0910\",\"SN\":\"1813CP617659\",\"TransType\":1}\n"
//        println(Gson().toJson(JsonReader(StringReader(test))))
//        println(Gson().toJson(JsonParser.parseString(test)))

        NumberFormat.getInstance().apply {
            maximumFractionDigits = 2

            println(String.format("Amount: %s", format(2000.00000)))
        }
    }

    fun parseHexStr2Byte(hexStr: String): ByteArray? {
        if (hexStr.isEmpty()) return null

        val result = ByteArray(hexStr.length / 2)

        for (i in 0 until hexStr.length / 2) {
            val high = hexStr.substring(i * 2, i * 2 + 1).toInt(16)
            val low = hexStr.substring(i * 2 + 1, i * 2 + 2).toInt(
                    16)
            result[i] = (high * 16 + low).toByte()
        }
        return result
    }

    @Test
    fun deleteCountry() {
        val phone = "+91 811 948 4898"

        val phoneInstance = PhoneNumberUtil.getInstance()
        val formatted = try {
            val phoneNumber = phoneInstance.parse(phone, "NG")
            phoneNumber?.nationalNumber?.toString() ?: phone

        } catch (_: Exception) {
            "Error"
        }

        print("The number is now $formatted")
    }

    @Test
    fun date() {
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")

        val date = LocalDateTime.of(2021, 10, 12, 0, 0)
                .minus(10, ChronoUnit.YEARS).toInstant(ZoneOffset.UTC)
                .toEpochMilli()

        val formattedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.of("UTC")).format(dateFormatter)

        System.out.println("The date is $formattedDate")


        val shortFormatter = DateTimeFormatter.ofPattern("MM/uu")

        println("The Date with month and year only is ${YearMonth.parse("12/20", shortFormatter).isAfter(YearMonth.now())}")
    }

    @Test
    fun testTime() {
        val time = "2022-06-22 08:32:47"

        val date = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").parse(time).run {
            LocalDateTime.from(this).toInstant(ZoneOffset.UTC).toEpochMilli()
        }

        print("$date")
    }

    @Test
    fun testPaymentTime() {
        val time = "2022-06-29T10:34:44.000000Z"

        println(Instant.parse(time).toEpochMilli())
    }

    @Test
    fun testBalanceFormatting() {
        val balance = "1,000,000.00012"

//        println(100.01.formatToBalance("\u20A6"))
//        println(balance.convertBalanceToNumber())
    }

    @Test
    fun testTimeBackwards() {
        LocalDateTime.ofInstant(Instant.ofEpochMilli(1660363168000), ZoneId.systemDefault()).run {
            println(format(DateTimeFormatter.ISO_DATE))
        }
    }

    @Test
    fun testDateFormat() {
        val date = "2022-08-31 08:26 AM".lowercase()

//        LocalDateTime.ofInstant(Instant.from(formatter.parse(date)), ZoneId.systemDefault()).also {
//            println(it.format(DateTimeFormatter.ISO_INSTANT))
//        }
    }

    @Test
    fun testZoneIDS(){
        println(ZonedDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()-60*60*24*1000),
                ZoneId.systemDefault()))
        println(LocalDateTime.now().atZone(ZoneId.systemDefault())
                .withZoneSameInstant(ZoneId.of("Africa/Lagos")))
        ZoneId.getAvailableZoneIds().sorted().forEach {
            println(it)
        }
    }
}