package com.dojah.kyc_sdk_kotlin.data.io

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import java.util.UUID
import androidx.core.content.edit

object DeviceIdManager {
    private const val PREF_NAME = "dojah_persistent_device_id_store"
    private const val KEY_DEVICE_ID = "dojah_persistent_device_id"

    @RequiresApi(Build.VERSION_CODES.N)
    fun get(context: Context): String {
        val protectedContext = context.createDeviceProtectedStorageContext()
        val prefs = protectedContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        var id = prefs.getString(KEY_DEVICE_ID, null)

        if (id == null) {
            id = UUID.randomUUID().toString()  // Generate once
            prefs.edit(commit = true) { putString(KEY_DEVICE_ID, id) } // must commit(), not apply()
        }

        return id
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("HardwareIds")
    fun androidId(context: Context): String {

        val protectedContext = context.createDeviceProtectedStorageContext()
        val prefs = protectedContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        var id = prefs.getString(KEY_DEVICE_ID, null)

        if (id == null) {
            id = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )

            prefs.edit(commit = true) { putString(KEY_DEVICE_ID, id) } // must commit(), not apply()
        }

        return id ?: ""
    }
}