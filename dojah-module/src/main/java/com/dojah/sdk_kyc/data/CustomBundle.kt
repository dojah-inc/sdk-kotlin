//package com.dojah.sdk_kyc.data
//
//import android.os.Build
//import android.os.Bundle
//import android.os.Parcelable
//import androidx.navigation.NavType
//import kotlinx.parcelize.Parcelize
//import kotlinx.serialization.DeserializationStrategy
//import kotlinx.serialization.json.Json
//import java.io.Serializable
//
//@kotlinx.serialization.Serializable
//@Parcelize
//class CustomBundle : Serializable {
//    val bundle: Bundle = Bundle()
//
//    fun putString(key: String, value: String) {
//        bundle.putString(key, value)
//    }
//    fun getString(key: String, defaultValue: String) {
//        bundle.getString(key,defaultValue)
//    }
//}
//
//@kotlinx.serialization.Serializable
//@Parcelize
//data class SearchParameters(
//    val searchQuery: String,
//    val filters: List<String>
//) : Parcelable
//
//val SearchParametersType = object : NavType<SearchParameters>(
//    isNullableAllowed = false
//) {
//    override fun put(bundle: Bundle, key: String, value: SearchParameters) {
//        bundle.putParcelable(key, value)
//    }
//    override fun get(bundle: Bundle, key: String): SearchParameters {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            bundle.getParcelable<SearchParameters>(key,SearchParameters::class.java)!!
//        } else {
//            TODO("VERSION.SDK_INT < TIRAMISU")
//        }
//    }
//
//    override fun parseValue(value: String): SearchParameters {
//        return Json.decodeFromString(,value)
//    }
//
//    // Only required when using Navigation 2.4.0-alpha07 and lower
//    override val name = "SearchParameters"
//}