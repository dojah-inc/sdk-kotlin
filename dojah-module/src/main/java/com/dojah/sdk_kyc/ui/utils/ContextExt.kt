package com.dojah.sdk_kyc.ui.utils

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.provider.Settings
import android.util.TypedValue
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import androidx.documentfile.provider.DocumentFile
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.dojah.sdk_kyc.BuildConfig
import com.dojah.sdk_kyc.R
import timber.log.Timber
import java.io.File

fun Context.getColorPrimary(): Int {
    val tv = TypedValue()

    theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, tv, true)

    return tv.data
}

fun Context.getColorBackground(): Int {
    val tv = TypedValue()

    theme.resolveAttribute(android.R.attr.colorBackground, tv, true)

    return tv.data
}

fun Context.getTextColor(): Int {
    val tv = TypedValue()

    theme.resolveAttribute(android.R.attr.textColor, tv, true)

    return tv.data
}

fun Context.getAttr(attrId: Int): Int {
    val tv = TypedValue()

    theme.resolveAttribute(attrId, tv, true)

    return tv.data
}

fun Context.updatePrimaryColor(stringColor: String) {
    val tv = TypedValue()

    theme.resolveAttribute(androidx.appcompat.R.attr.colorPrimary, tv, true)
    tv.data = R.color.black
}

fun Context.createTemporaryFile(): Uri {
    val dir = if (BuildConfig.DEBUG) getExternalFilesDir("user") else File(filesDir, "user")

    return dir?.let {
        File.createTempFile("temp-user-profile-pic", ".png")
        FileProvider.getUriForFile(this, BuildConfig.FILE_PROVIDER_AUTHORITY, dir)

    } ?: Uri.EMPTY
}

fun Context.queryUriForPhoneNumber(phoneUtil: PhoneNumberUtil, uri: Uri): String {
    //The URI returned from the ResultLauncher belongs to the Contacts table
    //which doesn't the phone numbers, those are in the Phone table
    //but the ID in Contacts table is a foreign key in Phone table(ID (in Contacts) == CONTACT_ID (in Phone))
    //so query Contacts for the id then use that to search through Phone table

    return contentResolver.run {
        val contactProj = arrayOf(ContactsContract.Contacts._ID)

        val contactsCursor = query(uri, contactProj, null, null, null)
        if (contactsCursor?.moveToNext() == true) {
            val id = contactsCursor.getString(0)
            val where = "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} =?"
            val phoneProj = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)

            contactsCursor.close()

            val phoneCursor = query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                phoneProj,
                where,
                arrayOf(id),
                null
            )
            val phoneNumber =
                if (phoneCursor?.moveToNext() == true) phoneCursor.getString(0) else ""
            phoneCursor?.close()

            Timber.d("Phone number is $phoneNumber")

            parseNumber(phoneUtil, phoneNumber)

        } else ""
    }
}

private fun parseNumber(phoneUtil: PhoneNumberUtil, number: String): String {
    val phoneNumber = phoneUtil.parse(number, "NG")

    return (phoneNumber?.nationalNumber?.toString() ?: number).run {
        if (length == 11) this
        else "0$this"
    }
}

fun Context.getUriExtension(uri: Uri): String? {
    val uriType = if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
        contentResolver.getType(uri)
    } else DocumentFile.fromSingleUri(this, uri)?.type

    return MimeTypeMap.getSingleton().getExtensionFromMimeType(uriType)
}

fun Context.openAppSystemSettings() {
    startActivity(Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.fromParts("package", packageName, null)
    })
}